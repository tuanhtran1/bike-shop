package com.shop.bike.consumer.service.impl;

import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.consumer.repository.CouponDiscountConsumerRepository;
import com.shop.bike.consumer.repository.MyOrderConsumerRepository;
import com.shop.bike.consumer.service.CouponDiscountConsumerService;
import com.shop.bike.consumer.service.MyOrderConsumerService;
import com.shop.bike.consumer.vm.CouponDiscountConsumerVM;
import com.shop.bike.consumer.vm.mapper.CouponDiscountConsumerVMMapper;
import com.shop.bike.entity.CouponDiscount;
import com.shop.bike.entity.MyOrder;
import com.shop.bike.entity.enumeration.CouponStatus;
import com.shop.bike.entity.enumeration.ErrorEnum;
import com.shop.bike.entity.enumeration.OrderStatus;
import com.shop.bike.security.SecurityUtils;
import com.shop.bike.service.impl.CouponDiscountServiceImpl;
import com.shop.bike.web.rest.errors.BadRequestAlertException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	
	@Autowired
	@Qualifier(ApplicationConstant.CONSUMER)
	private MyOrderConsumerRepository myOrderConsumerRepository;
	
	
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
	public Optional<CouponDiscountConsumerVM> getOneByCode(String code) {
		Optional<CouponDiscount> couponDiscount = couponDiscountConsumerRepository.getRunningCouponByCode(code);
		return couponDiscount.map(coupon ->{
			validateCoupon(coupon);
			return couponDiscountConsumerVMMapper.toDto(coupon);
		});
	}
	
	private void validateCoupon(CouponDiscount coupon){
		Instant startDate = coupon.getStartDate();
		Instant endDate = coupon.getEndDate();
		Instant now = Instant.now();
		
		if(endDate == null && now.isBefore(startDate))
			throw new BadRequestAlertException(ErrorEnum.COUPON_IS_DEACTIVATED);
		
		if(endDate != null && (now.isBefore(startDate) || endDate.isBefore(now)))
			throw new BadRequestAlertException(ErrorEnum.COUPON_IS_DEACTIVATED);
		
		if(coupon.getQuantityLimit() != null && coupon.getQuantityLimit() <= coupon.getQuantityUsed())
			throw new BadRequestAlertException(ErrorEnum.COUPON_IS_NOT_AVAILABLE);
		
		if(isCouponUsedUp(coupon))
			throw new BadRequestAlertException(ErrorEnum.COUPON_IS_USED_UP);
	}
	
	private boolean isCouponUsedUp(CouponDiscount couponDiscount) {
		log.debug("Loading orders for user....");
		Long buyerId = Long.valueOf(SecurityUtils.getCurrentUserLogin().get());
		if(couponDiscount.getQuantityLimitForUser() != null){
			List<MyOrder> ordersHaveApplyCoupon = myOrderConsumerRepository.findAllByBuyerId(buyerId).stream()
					.filter(c -> c.getCouponDiscountId() != null
							&& c.getCouponDiscountId().equals(couponDiscount.getId())
							&& c.getStatus() != OrderStatus.DELETED)
					.collect(Collectors.toList());
			if(!ordersHaveApplyCoupon.isEmpty()){
				return ordersHaveApplyCoupon.size() >= couponDiscount.getQuantityLimitForUser();
			}
		}
		return false;
	}
}
