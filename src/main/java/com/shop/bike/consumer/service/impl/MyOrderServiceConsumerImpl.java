package com.shop.bike.consumer.service.impl;

import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.consumer.dto.CreateOrderDTO;
import com.shop.bike.consumer.repository.MyOrderConsumerRepository;
import com.shop.bike.consumer.service.AddressConsumerService;
import com.shop.bike.consumer.service.MyOrderConsumerService;
import com.shop.bike.consumer.vm.MyOrderConsumerVM;
import com.shop.bike.consumer.vm.mapper.MyOrderConsumerVMMapper;
import com.shop.bike.entity.Address;
import com.shop.bike.entity.MyOrder;
import com.shop.bike.entity.enumeration.ErrorEnum;
import com.shop.bike.entity.enumeration.OrderStatus;
import com.shop.bike.entity.enumeration.PaymentGateway;
import com.shop.bike.repository.AddressRepository;
import com.shop.bike.security.SecurityUtils;
import com.shop.bike.service.CouponDiscountService;
import com.shop.bike.service.impl.MyOrderServiceImpl;
import com.shop.bike.utils.JsonConverter;
import com.shop.bike.vm.CouponDiscountVM;
import com.shop.bike.web.rest.errors.BadRequestAlertException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
@Qualifier(ApplicationConstant.CONSUMER)
@Slf4j
public class MyOrderServiceConsumerImpl extends MyOrderServiceImpl implements MyOrderConsumerService {
	
	@Autowired
	@Qualifier(ApplicationConstant.CONSUMER)
	private MyOrderConsumerRepository myOrderConsumerRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private MyOrderConsumerVMMapper myOrderConsumerVMMapper;
	
	@Autowired
	@Qualifier(ApplicationConstant.CONSUMER)
	private AddressConsumerService addressConsumerService;
	
	@Autowired
	private CouponDiscountService couponDiscountService;
	
	/*************************************************************
	 *
	 * @decription: create order by consumer
	 *
	 * @param: dto
	 * @return: vm
	 *
	 * @date: 23/04/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 **************************************************************/
	@Override
	public MyOrderConsumerVM createOrderByConsumer(CreateOrderDTO dto) {
		
		String userId = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.USER_NOT_FOUND));
		
		MyOrder newOrder = initOrder(dto);
		
		newOrder.setBuyerId(Long.valueOf(userId));
		
		newOrder = myOrderConsumerRepository.save(newOrder);
		
		//process coupon
		if(dto.getCouponCode() != null){
			CouponDiscountVM coupon = couponDiscountService.getOneByCode(dto.getCouponCode())
					.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.COUPON_NOT_FOUND));
			newOrder.setCouponDiscountId(coupon.getId());
			newOrder.setCouponDiscountCache(JsonConverter.toJson(coupon));
			newOrder.setCouponDiscount(getCashDiscount(newOrder.getTotal(), coupon.getDiscount(), coupon.getType()));
			newOrder.setDiscount(newOrder.getCouponDiscount());
		}
		//Set total
		BigDecimal total = newOrder.getSubTotal()
				.subtract(newOrder.getDiscount())
				.add(newOrder.getShippingFee());
		newOrder.setTotal(total);
		
		//save address
		Address shippingAddress = addressConsumerService.createShippingAddress(dto.getAddress());
		newOrder.setAddress(shippingAddress);
		newOrder.setShippingAddressCache(JsonConverter.toJson(shippingAddress));
		
		return myOrderConsumerVMMapper.toDto(myOrderConsumerRepository.save(newOrder));
	}
	
	@Override
	public void cancel(Long id, String note) {
		updateStatus(id, OrderStatus.CANCELED, note);
	}
	
	@Override
	public void paymentCash(Long id) {
		MyOrder myOrder = myOrderConsumerRepository.findById(id)
				.filter(o -> {
					Long userId = Long.valueOf(SecurityUtils.getCurrentUserLogin().get());
					if(SecurityUtils.isConsumer()) {
						return userId.equals(o.getBuyerId());
					} else return SecurityUtils.isAdmin();
				})
				.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.ORDER_NOT_FOUND));
		myOrder.setPaymentGateway(PaymentGateway.CASH);
		myOrderConsumerRepository.save(myOrder);
	}
	
	@Override
	public void paymentTrade(Long id) {
		MyOrder myOrder = myOrderConsumerRepository.findById(id)
				.filter(o -> {
					Long userId = Long.valueOf(SecurityUtils.getCurrentUserLogin().get());
					if(SecurityUtils.isConsumer()) {
						return userId.equals(o.getBuyerId());
					} else return SecurityUtils.isAdmin();
				})
				.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.ORDER_NOT_FOUND));
		myOrder.setPaymentGateway(PaymentGateway.TRADE);
		myOrder.setStatus(OrderStatus.ACCEPTED);
		myOrderConsumerRepository.save(myOrder);
	}
}
