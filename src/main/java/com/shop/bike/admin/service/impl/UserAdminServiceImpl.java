package com.shop.bike.admin.service.impl;

import com.shop.bike.admin.service.UserAdminService;
import com.shop.bike.admin.vm.ProfileAdminVM;
import com.shop.bike.admin.vm.mapper.ProfileAdminVMMapper;
import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.entity.User;
import com.shop.bike.entity.enumeration.ErrorEnum;
import com.shop.bike.security.SecurityUtils;
import com.shop.bike.service.impl.UserServiceImpl;
import com.shop.bike.web.rest.errors.BadRequestAlertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Qualifier(ApplicationConstant.ADMIN)
public class UserAdminServiceImpl extends UserServiceImpl implements UserAdminService {
	
	@Autowired
	private ProfileAdminVMMapper profileAdminVMMapper;
	
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
	@Override
	public ProfileAdminVM getCurrentProfileAdmin() {
		Long userId = Long.valueOf(SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.USER_NOT_FOUND)));
		return profileAdminVMMapper.toDto(this.findAdminById(userId)
				.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.USER_BLOCKED)));
	}
	
}
