package com.shop.bike.admin.service;

import com.shop.bike.admin.vm.ProfileAdminVM;
import com.shop.bike.entity.User;

import java.util.Optional;

public interface UserAdminService {
	
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
	ProfileAdminVM getCurrentProfileAdmin();
	
}
