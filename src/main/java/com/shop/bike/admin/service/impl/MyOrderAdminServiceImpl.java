package com.shop.bike.admin.service.impl;

import com.shop.bike.admin.dto.MyOrderFilterDTO;
import com.shop.bike.admin.repository.MyOrderAdminRepository;
import com.shop.bike.admin.service.MyOrderAdminService;
import com.shop.bike.admin.vm.MyOrderAdminVM;
import com.shop.bike.admin.vm.mapper.MyOrderAdminVMMapper;
import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.service.MyOrderDetailService;
import com.shop.bike.service.impl.MyOrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		return myOrderAdminRepository.findAll(filters,pageable).map(myOrderAdminVMMapper::toDto);
	}
}
