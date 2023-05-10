package com.shop.bike.admin.service;

import com.shop.bike.admin.dto.MyOrderFilterDTO;
import com.shop.bike.admin.pojo.StatisticOrder;
import com.shop.bike.admin.vm.MyOrderAdminVM;
import com.shop.bike.service.MyOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;

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
	
	void accept(Long id, String note);
	
	void shipping(Long id, String note);
	
	void delivered(Long id, String note);
	
	void done(Long id, String note);
	
	void reject(Long id, String note);
	
	StatisticOrder statistic(Instant fromDate, Instant toDate);
}
