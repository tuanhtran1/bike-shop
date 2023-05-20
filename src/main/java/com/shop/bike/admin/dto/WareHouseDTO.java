package com.shop.bike.admin.dto;

import com.shop.bike.entity.enumeration.WareHouseStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class WareHouseDTO {
	
	private Long id;
	
	private BigDecimal originPrice;
	
	private Integer quantity;
	
	@NotNull
	private Long tradingProductId;
	
	@NotNull
	private Instant date;
}
