package com.shop.bike.consumer.service;

import com.shop.bike.admin.dto.MyOrderFilterDTO;
import com.shop.bike.consumer.dto.CreateOrderDTO;
import com.shop.bike.consumer.vm.MyOrderConsumerVM;
import com.shop.bike.entity.MyOrder;
import com.shop.bike.service.MyOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MyOrderConsumerService extends MyOrderService {
	
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
	MyOrderConsumerVM createOrderByConsumer(CreateOrderDTO dto);
	
	void cancel(Long id, String note);
	
	void paymentCash(Long id);
	
	void paymentTrade(Long id);
	
	Page<MyOrderConsumerVM> findAllOrders(MyOrderFilterDTO filters, Pageable pageable);
	
	Optional<MyOrderConsumerVM> findById(Long id);
	
	List<MyOrder> findAllOrderConsumer();
}
