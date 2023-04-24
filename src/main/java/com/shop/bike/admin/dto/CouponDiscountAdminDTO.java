package com.shop.bike.admin.dto;

import com.shop.bike.entity.enumeration.DiscountType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

@Data
public class CouponDiscountAdminDTO {
	
	private Long id;
	
	@NotNull
	private String name;
	private String couponCode;
	
	private Integer quantityLimit;

	@NotNull
	private BigDecimal discount;
	
	@NotNull
	private DiscountType type;
	
	private BigDecimal maxDiscount;
	
	private Integer quantityLimitForUser;
	
	private Instant startDate;
	
	private Instant endDate;
}
