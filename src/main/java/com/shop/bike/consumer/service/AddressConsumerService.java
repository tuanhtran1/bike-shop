package com.shop.bike.consumer.service;

import com.shop.bike.consumer.dto.AddressDTO;
import com.shop.bike.entity.Address;
import com.shop.bike.service.AddressService;

import java.util.Map;

public interface AddressConsumerService extends AddressService {
	
	/*************************************************************
	 *
	 * @decription: create shipping address
	 *
	 * @param: dto
	 * @return: none
	 *
	 * @date: 12/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	**************************************************************/
	Address createShippingAddress(AddressDTO dto);
	
	/*************************************************************
	 *
	 * @decription: calculate kilometers address
	 *
	 * @param:
	 * @return:
	 *
	 * @date: 12/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	**************************************************************/
	Map<String,Object> calculateShippingAddress(Double lat, Double lng);
}
