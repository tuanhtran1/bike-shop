package com.shop.bike.consumer.service;

import com.shop.bike.admin.vm.ProfileAdminVM;
import com.shop.bike.consumer.vm.ProfileConsumerVM;

public interface UserConsumerService {
	
	/*************************************************************
	 *
	 * @decription: get current detail user
	 *
	 * @param: token when authenticate
	 * @return: profile vm
	 *
	 * @date: 04/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	**************************************************************/
	ProfileConsumerVM getCurrentProfileConsumer();
	
}
