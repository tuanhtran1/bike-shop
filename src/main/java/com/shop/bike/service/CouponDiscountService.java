package com.shop.bike.service;

import com.shop.bike.vm.CouponDiscountVM;

import java.util.Optional;

public interface CouponDiscountService {
	
	/*************************************************************
	 *
	 * @decription: find coupon by id
	 *
	 * @param:
	 * @return:
	 *
	 * @date: 11/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	**************************************************************/
	Optional<CouponDiscountVM> getOneByCode(String code);
}
