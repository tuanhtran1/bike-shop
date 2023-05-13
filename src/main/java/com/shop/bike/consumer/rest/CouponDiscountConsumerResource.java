package com.shop.bike.consumer.rest;

import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.consumer.service.CouponDiscountConsumerService;
import com.shop.bike.consumer.vm.CouponDiscountConsumerVM;
import com.shop.bike.utils.PaginationUtil;
import com.shop.bike.vm.CouponDiscountVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/consumer")
@Slf4j
public class CouponDiscountConsumerResource {
	
	private static final String ENTITY_NAME = "coupon_discount";
	
	@Autowired
	@Qualifier(ApplicationConstant.CONSUMER)
	private CouponDiscountConsumerService couponDiscountConsumerService;
	
	@GetMapping("/coupon-discounts")
	public ResponseEntity<List<CouponDiscountConsumerVM>> getListRunningCoupon(@RequestParam(value = "keyword",required = false) String keyword,
																			   Pageable pageable) {
		log.debug("REST request to get list running Coupons");
		Page<CouponDiscountConsumerVM> page = couponDiscountConsumerService.getRunningListCoupons(keyword, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}
	
	@GetMapping("/coupon-discounts/{id}")
	public ResponseEntity<CouponDiscountConsumerVM> getDetailCoupon(@PathVariable("id") Long id) {
		log.debug("REST request to get detail coupon");
		CouponDiscountConsumerVM couponDiscountVM = couponDiscountConsumerService.getDetailByConsumer(id);
		return ResponseEntity.ok().body(couponDiscountVM);
	}
	
	@GetMapping("/coupon")
	public ResponseEntity<CouponDiscountVM> getDetailCouponCode(@RequestParam String code) {
		log.debug("REST request to get detail coupon {}", code);
		Optional<CouponDiscountVM> couponDiscountVM = couponDiscountConsumerService.getOneByCode(code);
		return couponDiscountVM.map(response -> ResponseEntity.ok().body(response))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}
