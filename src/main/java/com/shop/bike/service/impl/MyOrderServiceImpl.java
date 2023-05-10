package com.shop.bike.service.impl;

import com.shop.bike.consumer.dto.CreateOrderDTO;
import com.shop.bike.entity.MyOrder;
import com.shop.bike.entity.OrderAuditing;
import com.shop.bike.entity.enumeration.*;
import com.shop.bike.repository.MyOrderRepository;
import com.shop.bike.security.SecurityUtils;
import com.shop.bike.service.MyOrderDetailService;
import com.shop.bike.service.MyOrderService;
import com.shop.bike.utils.GenerateIDUtil;
import com.shop.bike.web.rest.errors.BadRequestAlertException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;

@Service
@Transactional
@Primary
@Slf4j
public class MyOrderServiceImpl implements MyOrderService {
	
	@Autowired
	private MyOrderDetailService myOrderDetailService;
	
	@Autowired
	private MyOrderRepository myOrderRepository;
	
	private static final BigDecimal shippingFee = BigDecimal.valueOf(50000.0);
	
	protected MyOrder initOrder(CreateOrderDTO myOrderDTO) {
		
		//Initialize an order
		MyOrder newOrder = new MyOrder();
		
		newOrder.setPaymentGateway(myOrderDTO.getPaymentGateway());
		
		//Set order code
		newOrder.setCode(GenerateIDUtil.getOrderCode());
		
		//Set default status
		setAudit(newOrder, OrderStatus.WAITING);
		
		//Set default payment status
		newOrder.setPaymentStatus(PaymentStatus.PENDING);
		
		//Set default deliver status
		newOrder.setDeliverStatus(DeliveryStatus.PENDING);
		
		//Set note
		newOrder.setNote(myOrderDTO.getNote());
		
		//Set orderDetails
		myOrderDetailService.create(newOrder, myOrderDTO.getOrderDetails());
		
		//process shipping fee
		newOrder.setShippingFee(shippingFee);
		
		//process shipping fee
		newOrder.setDiscount(BigDecimal.ZERO);
		
		//Set total
		BigDecimal total = newOrder.getSubTotal()
				.subtract(newOrder.getDiscount())
				.add(newOrder.getShippingFee());
		newOrder.setTotal(total);
		
		return newOrder;
	}
	
	protected void setAudit(MyOrder existingOrder, OrderStatus orderStatus) {
		if(existingOrder.getStatus() != null && existingOrder.getStatus().equals(orderStatus)) return;
		log.debug("Adding auditInfo");
		OrderAuditing orderAuditingDTO = new OrderAuditing(existingOrder.getAudit());
		Instant now = Instant.now();
		switch (orderStatus) {
			case ACCEPTED:
				orderAuditingDTO.setAcceptedDate(now);
				break;
			case CANCELED:
				if(!SecurityUtils.isAdmin()){
					if(orderAuditingDTO.getAcceptedDate() != null) {
						throw new BadRequestAlertException(ErrorEnum.CAN_NOT_CANCEL_ACCEPTED_ORDER);
					}
				}
				if(orderAuditingDTO.getDoneDate() != null){
					throw new BadRequestAlertException(ErrorEnum.CAN_NOT_CANCEL_DONE_ORDER);
				}
				orderAuditingDTO.setCanceledDate(now);
				break;
			case DELETED:
				orderAuditingDTO.setDeletedDate(now);
				break;
			case DELIVERED:
				orderAuditingDTO.setDeliveredDate(now);
				break;
			case REJECTED:
				orderAuditingDTO.setRejectedDate(now);
				break;
			case WAITING:
				orderAuditingDTO.setWaitingDate(now);
				if(orderAuditingDTO.getCreatedDate() == null) orderAuditingDTO.setCreatedDate(now);
				break;
			case SHIPPING:
				orderAuditingDTO.setShippingDate(now);
				break;
			case DONE:
				if(existingOrder.getPaymentGateway().equals(PaymentGateway.CASH)){
					orderAuditingDTO.setPaymentDate(now);
				}
				orderAuditingDTO.setDoneDate(now);
				break;
			default:
				break;
		}
		existingOrder.setAudit(orderAuditingDTO.toString());
		existingOrder.setStatus(orderStatus);
	}
	
	protected MyOrder updateStatus(Long id, OrderStatus status, String note) {
		MyOrder myOrder = myOrderRepository.findById(id)
				.filter(o -> {
					Long userId = Long.valueOf(SecurityUtils.getCurrentUserLogin().get());
					if(SecurityUtils.isConsumer()) {
						return userId.equals(o.getBuyerId());
					} else return SecurityUtils.isAdmin();
				})
				.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.ORDER_NOT_FOUND));
		setAudit(myOrder, status);
		myOrder.setStatus(status);
		myOrder.setNote(note);
		return myOrderRepository.save(myOrder);
	}
}
