package com.shop.bike.consumer.service;

import com.shop.bike.consumer.dto.CreateOrderDTO;
import com.shop.bike.consumer.vm.MyOrderConsumerVM;
import com.shop.bike.service.MyOrderService;

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
}
