package com.shop.bike.admin.vm;

import com.shop.bike.entity.enumeration.WareHouseStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class WareHouseVM {
	
	private Long id;
	
	private WareHouseStatus status;
	
	private BigDecimal originPrice;
	
	private Integer quantity;
	
	private Long tradingProductId;
	
	private Instant date;
}
