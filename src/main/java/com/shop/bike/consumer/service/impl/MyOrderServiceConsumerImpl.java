package com.shop.bike.consumer.service.impl;

import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.consumer.dto.CreateOrderDTO;
import com.shop.bike.consumer.repository.MyOrderConsumerRepository;
import com.shop.bike.consumer.service.MyOrderConsumerService;
import com.shop.bike.consumer.vm.MyOrderConsumerVM;
import com.shop.bike.consumer.vm.mapper.MyOrderConsumerVMMapper;
import com.shop.bike.entity.Address;
import com.shop.bike.entity.MyOrder;
import com.shop.bike.entity.enumeration.ErrorEnum;
import com.shop.bike.entity.enumeration.OrderStatus;
import com.shop.bike.repository.AddressRepository;
import com.shop.bike.security.SecurityUtils;
import com.shop.bike.service.MyOrderService;
import com.shop.bike.service.impl.MyOrderServiceImpl;
import com.shop.bike.web.rest.errors.BadRequestAlertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Qualifier(ApplicationConstant.CONSUMER)
public class MyOrderServiceConsumerImpl extends MyOrderServiceImpl implements MyOrderConsumerService {
	
	@Autowired
	@Qualifier(ApplicationConstant.CONSUMER)
	private MyOrderConsumerRepository myOrderConsumerRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private MyOrderConsumerVMMapper myOrderConsumerVMMapper;
	
	/*************************************************************
	 *
	 * @decription: create order by consumer
	 *
	 * @param: dto
	 * @return: vm
	 *
	 * @date: 23/04/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 *************************************************************
	 * @param dto*/
	@Override
	public MyOrderConsumerVM createOrderByConsumer(CreateOrderDTO dto) {
		
		String userId = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.USER_NOT_FOUND));
		
		MyOrder newOrder = initOrder(dto);
		
		newOrder.setBuyerId(Long.valueOf(userId));
		
		//Address
//		Address address = addressRepository.findById(dto.getShippingAddressId()).get();
//		newOrder.setAddress(address);
		
		return myOrderConsumerVMMapper.toDto(myOrderConsumerRepository.save(newOrder));
	}
	
	@Override
	public void cancel(Long id, String note) {
		updateStatus(id, OrderStatus.CANCELED, note);
	}
}
