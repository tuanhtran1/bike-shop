package com.shop.bike.admin.service.impl;

import com.shop.bike.admin.dto.MyOrderFilterDTO;
import com.shop.bike.admin.pojo.PaymentOrderStatistic;
import com.shop.bike.admin.pojo.StatisticOrder;
import com.shop.bike.admin.repository.MyOrderAdminRepository;
import com.shop.bike.admin.service.MyOrderAdminService;
import com.shop.bike.admin.vm.MyOrderAdminVM;
import com.shop.bike.admin.vm.mapper.MyOrderAdminVMMapper;
import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.entity.enumeration.OrderStatus;
import com.shop.bike.entity.view.ViewRevenueConsumer;
import com.shop.bike.repository.UserRepository;
import com.shop.bike.repository.ViewRevenueConsumerRepository;
import com.shop.bike.service.MyOrderDetailService;
import com.shop.bike.service.UserService;
import com.shop.bike.service.impl.MyOrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
@Qualifier(ApplicationConstant.ADMIN)
@Slf4j
public class MyOrderAdminServiceImpl extends MyOrderServiceImpl implements MyOrderAdminService {
	
	@Autowired
	private MyOrderDetailService myOrderDetailService;
	
	@Autowired
	@Qualifier(ApplicationConstant.ADMIN)
	private MyOrderAdminRepository myOrderAdminRepository;
	
	@Autowired
	private MyOrderAdminVMMapper myOrderAdminVMMapper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ViewRevenueConsumerRepository viewRevenueConsumerRepository;
	
	
	/*************************************************************
	 *
	 * @decription: find all by admin
	 *
	 * @param: filters, pageable
	 * @return: page
	 *
	 * @date: 04/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 **************************************************************/
	@Override
	public Page<MyOrderAdminVM> findAllByAdmin(MyOrderFilterDTO filters, Pageable pageable) {
		return myOrderAdminRepository.findAll(filters,pageable).map(order ->{
			MyOrderAdminVM vm = myOrderAdminVMMapper.toDto(order);
			vm.setBuyerName(userService.findById(order.getBuyerId()).get().getName());
			return vm;
		});
	}
	
	@Override
	public void accept(Long id, String note) {
		updateStatus(id, OrderStatus.ACCEPTED, note);
	}
	
	@Override
	public void shipping(Long id, String note) {
		updateStatus(id, OrderStatus.SHIPPING, note);
	}
	
	@Override
	public void delivered(Long id, String note) {
		updateStatus(id, OrderStatus.DELIVERED, note);
	}
	
	@Override
	public void done(Long id, String note) {
		updateStatus(id, OrderStatus.DONE, note);
	}
	
	@Override
	public void reject(Long id, String note) {
		updateStatus(id, OrderStatus.REJECTED, note);
	}
	
	@Override
	@Transactional(readOnly = true)
	public StatisticOrder statistic(Instant fromDate, Instant toDate) {
		return myOrderAdminRepository.orderStatistic(fromDate, toDate);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<ViewRevenueConsumer> getRevenueConsumer(Instant fromDate, Instant toDate, Pageable pageable) {
		return viewRevenueConsumerRepository.getViewRevenueConsumer(fromDate, toDate, pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public PaymentOrderStatistic paymentStatistic(Instant fromDate, Instant toDate) {
		return myOrderAdminRepository.paymentStatistic(fromDate, toDate);
	}
	
}
