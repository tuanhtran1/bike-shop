package com.shop.bike.service.impl;

import com.shop.bike.repository.CouponDiscountRepository;
import com.shop.bike.service.CouponDiscountService;
import com.shop.bike.vm.mapper.CouponDiscountVMMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Primary
public class CouponDiscountServiceImpl implements CouponDiscountService {
	
	@Autowired
	private CouponDiscountRepository couponDiscountRepository;
	
	@Autowired
	private CouponDiscountVMMapper couponDiscountVMMapper;
	
}
