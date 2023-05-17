package com.shop.bike.consumer.vm;

import com.shop.bike.entity.enumeration.DiscountType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CouponDiscountConsumerVM {
	
	private Long id;
	
	private String name;
	
	private String couponCode;
	
	private Integer quantityLimit;
	
	private Integer quantityLimitForUser;

	private BigDecimal discount;
	
	private DiscountType type;
	
	private BigDecimal maxDiscount;
	
}
