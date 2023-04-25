package com.shop.bike.admin.dto;

import com.shop.bike.entity.enumeration.CouponStatus;
import com.shop.bike.entity.enumeration.DiscountType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

@Data
public class CouponDiscountFiltersDTO {
	
	private String keyword;
	private Long id;
	
	private CouponStatus status;
	private String name;
	private String couponCode;
	
	private Integer quantityLimitFrom;
	
	private Integer quantityLimitTo;
	
	private BigDecimal discount;
	
	private DiscountType type;
	
	private BigDecimal maxDiscountFrom;
	
	private BigDecimal maxDiscountTo;
	
	private Integer quantityLimitForUserFrom;
	
	private Integer quantityLimitForUserTo;
	
	private Instant startDateFrom;
	
	private Instant startDateTo;
	
	private Instant endDateFrom;
	
	private Instant endDateTo;
	
	
}
