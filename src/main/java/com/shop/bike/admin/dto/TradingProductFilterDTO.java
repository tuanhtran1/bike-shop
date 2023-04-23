package com.shop.bike.admin.dto;

import com.shop.bike.entity.enumeration.ActionStatus;
import com.shop.bike.entity.enumeration.ApproveStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TradingProductFilterDTO {
	
	private String keyword;
	
	private String itemCode;
	
	private String name;
	
	private Long userId;
	
	private BigDecimal priceFrom;
	
	private BigDecimal priceTo;
	
	private ActionStatus status;
	
	private ApproveStatus approveStatus;
	
}
