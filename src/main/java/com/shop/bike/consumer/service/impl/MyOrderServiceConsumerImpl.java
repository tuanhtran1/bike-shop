package com.shop.bike.consumer.service.impl;

import com.shop.bike.admin.dto.MyOrderFilterDTO;
import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.consumer.dto.CreateOrderDTO;
import com.shop.bike.consumer.repository.MyOrderConsumerRepository;
import com.shop.bike.consumer.service.AddressConsumerService;
import com.shop.bike.consumer.service.CouponDiscountConsumerService;
import com.shop.bike.consumer.service.MyOrderConsumerService;
import com.shop.bike.consumer.service.UserConsumerService;
import com.shop.bike.consumer.vm.CouponDiscountConsumerVM;
import com.shop.bike.consumer.vm.MyOrderConsumerVM;
import com.shop.bike.consumer.vm.mapper.MyOrderConsumerVMMapper;
import com.shop.bike.entity.Address;
import com.shop.bike.entity.CouponDiscount;
import com.shop.bike.entity.MyOrder;
import com.shop.bike.entity.enumeration.ErrorEnum;
import com.shop.bike.entity.enumeration.OrderStatus;
import com.shop.bike.entity.enumeration.PaymentGateway;
import com.shop.bike.entity.enumeration.PaymentStatus;
import com.shop.bike.repository.AddressRepository;
import com.shop.bike.security.SecurityUtils;
import com.shop.bike.service.impl.MyOrderServiceImpl;
import com.shop.bike.utils.JsonConverter;
import com.shop.bike.web.rest.errors.BadRequestAlertException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	@Qualifier(ApplicationConstant.CONSUMER)
	private CouponDiscountConsumerService couponDiscountService;
	
	@Autowired
	@Qualifier(ApplicationConstant.CONSUMER)
	private UserConsumerService userConsumerService;
	
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
			processCoupon(dto.getCouponCode(), newOrder);
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
		
		MyOrderConsumerVM orderVM = myOrderConsumerVMMapper.toDto(myOrderConsumerRepository.save(newOrder));
		orderVM.setBuyerName(userConsumerService.getCurrentProfileConsumer().getName());
		return orderVM;
	}
	
	private void processCoupon(String couponCode, MyOrder newOrder) {
		CouponDiscountConsumerVM coupon = couponDiscountService.getOneByCode(couponCode)
				.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.COUPON_NOT_FOUND));
		newOrder.setCouponDiscountId(coupon.getId());
		newOrder.setCouponDiscountCache(JsonConverter.toJson(coupon));
		BigDecimal cashDiscount = getCashDiscount(newOrder.getSubTotal(), coupon.getDiscount(), coupon.getType());
		if(coupon.getMaxDiscount() != null){
			BigDecimal maxDiscount = coupon.getMaxDiscount();
			newOrder.setCouponDiscount(cashDiscount.compareTo(maxDiscount) > 0 ? maxDiscount : cashDiscount);
		}
		else newOrder.setCouponDiscount(cashDiscount);
		
		newOrder.setDiscount(newOrder.getCouponDiscount());
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
		myOrder.setPaymentStatus(PaymentStatus.SUCCESSED);
		myOrder.setStatus(OrderStatus.ACCEPTED);
		myOrderConsumerRepository.save(myOrder);
	}
	
	@Override
	public Page<MyOrderConsumerVM> findAllOrders(MyOrderFilterDTO filters, Pageable pageable) {
		Long buyerId = Long.valueOf(SecurityUtils.getCurrentUserLogin().get());
		filters.setBuyerId(buyerId);
		return myOrderConsumerRepository.findAll(filters, pageable).map(myOrderConsumerVMMapper::toDto);
	}
	
	@Override
	public Optional<MyOrderConsumerVM> findById(Long id) {
		return myOrderConsumerRepository.findById(id).map(o ->{
			MyOrderConsumerVM vm = myOrderConsumerVMMapper.toDto(o);
			vm.setBuyerName(userConsumerService.getCurrentProfileConsumer().getName());
			vm.setPhone(userConsumerService.getCurrentProfileConsumer().getPhone());
			return vm;
		});
	}
	
	@Override
	public List<MyOrder> findAllOrderConsumer() {
		Long buyerId = Long.valueOf(SecurityUtils.getCurrentUserLogin().get());
		return myOrderConsumerRepository.findAllByBuyerId(buyerId);
	}
	
	
}
