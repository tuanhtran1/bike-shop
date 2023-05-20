package com.shop.bike.consumer.service;

import com.shop.bike.admin.vm.ProfileAdminVM;
import com.shop.bike.consumer.dto.ProfileConsumerDTO;
import com.shop.bike.consumer.dto.RegisterConsumerDTO;
import com.shop.bike.consumer.vm.ProfileConsumerVM;
import com.shop.bike.service.UserService;
import org.springframework.http.ResponseEntity;

public interface UserConsumerService extends UserService {
	
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
	
	void checkExist(String email);
	
	ProfileConsumerVM createProfileConsumer(RegisterConsumerDTO dto);
	
	ProfileConsumerVM updateProfile(ProfileConsumerDTO dto);
}
