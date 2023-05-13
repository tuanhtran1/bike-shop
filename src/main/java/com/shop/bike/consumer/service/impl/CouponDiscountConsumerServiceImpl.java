package com.shop.bike.consumer.service.impl;

import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.consumer.repository.CouponDiscountConsumerRepository;
import com.shop.bike.consumer.service.CouponDiscountConsumerService;
import com.shop.bike.consumer.vm.CouponDiscountConsumerVM;
import com.shop.bike.consumer.vm.mapper.CouponDiscountConsumerVMMapper;
import com.shop.bike.entity.enumeration.CouponStatus;
import com.shop.bike.entity.enumeration.ErrorEnum;
import com.shop.bike.service.impl.CouponDiscountServiceImpl;
import com.shop.bike.web.rest.errors.BadRequestAlertException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Qualifier(ApplicationConstant.CONSUMER)
@Slf4j
public class CouponDiscountConsumerServiceImpl extends CouponDiscountServiceImpl implements CouponDiscountConsumerService {
	
	@Autowired
	@Qualifier(ApplicationConstant.CONSUMER)
	private CouponDiscountConsumerRepository couponDiscountConsumerRepository;
	
	@Autowired
	private CouponDiscountConsumerVMMapper couponDiscountConsumerVMMapper;
	
	
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
	@Override
	public Page<CouponDiscountConsumerVM> getRunningListCoupons(String keyword, Pageable pageable) {
		return couponDiscountConsumerRepository.findAll(keyword, pageable).map(couponDiscountConsumerVMMapper::toDto);
	}
	
	/*************************************************************
	 *
	 * @decription: get detail by consumer
	 *
	 * @param: id
	 * @return: vm
	 *
	 * @date: 11/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 *************************************************************/
	@Override
	public CouponDiscountConsumerVM getDetailByConsumer(Long id) {
		return couponDiscountConsumerVMMapper.toDto(couponDiscountConsumerRepository.findByIdAndStatusNot(id, CouponStatus.DELETED)
				.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.COUPON_NOT_FOUND)));
	}
}
