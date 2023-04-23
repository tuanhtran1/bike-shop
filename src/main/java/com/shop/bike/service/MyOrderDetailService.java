package com.shop.bike.service;

import com.shop.bike.consumer.dto.CreateOrderDetailDTO;
import com.shop.bike.entity.MyOrder;
import com.shop.bike.entity.MyOrderDetails;

import java.util.List;
import java.util.Set;

public interface MyOrderDetailService {
	
	Set<MyOrderDetails> create(MyOrder newOrder, List<CreateOrderDetailDTO> orderDetailsDTO);
}
