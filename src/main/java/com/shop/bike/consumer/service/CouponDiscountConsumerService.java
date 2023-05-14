package com.shop.bike.consumer.service;

import com.shop.bike.consumer.vm.CouponDiscountConsumerVM;
import com.shop.bike.service.CouponDiscountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CouponDiscountConsumerService extends CouponDiscountService {
	
	
	/*************************************************************
	 *
	 * @decription: get running coupons
	 *
	 * @param: keyword
	 * @return: pages
	 *
	 * @date: 11/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	**************************************************************/
	Page<CouponDiscountConsumerVM> getRunningListCoupons(String keyword, Pageable pageable);
	
	/*************************************************************
	 *
	 * @decription: get detail by consumer
	 *
	 * @param: id
	 * @return: vm
	 *
	 * @date: 11/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	**************************************************************/
	CouponDiscountConsumerVM getDetailByConsumer(Long id);
}
