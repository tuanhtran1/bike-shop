package com.shop.bike.service.impl;

import com.shop.bike.entity.CouponDiscount;
import com.shop.bike.repository.CouponDiscountRepository;
import com.shop.bike.service.CouponDiscountService;
import com.shop.bike.vm.CouponDiscountVM;
import com.shop.bike.vm.mapper.CouponDiscountVMMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Primary
public class CouponDiscountServiceImpl implements CouponDiscountService {
	
	@Autowired
	private CouponDiscountRepository couponDiscountRepository;
	
	@Autowired
	private CouponDiscountVMMapper couponDiscountVMMapper;
	
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
	@Override
	public Optional<CouponDiscountVM> getOneByCode(String code) {
		Optional<CouponDiscount> couponDiscount = couponDiscountRepository.getRunningCouponByCode(code);
		return couponDiscount.map(couponDiscountVMMapper::toDto);
	}
}
