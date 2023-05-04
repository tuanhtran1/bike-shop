package com.shop.bike.admin.service;

import com.shop.bike.admin.dto.MyOrderFilterDTO;
import com.shop.bike.admin.vm.MyOrderAdminVM;
import com.shop.bike.service.MyOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MyOrderAdminService extends MyOrderService {
	
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
	Page<MyOrderAdminVM> findAllByAdmin(MyOrderFilterDTO filters, Pageable pageable);
}
