package com.shop.bike.consumer.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CreateOrderDetailDTO {
	
	@NotNull
	private Long productTradingId;
	
	@NotNull
	private Integer quantity;
	
	@NotNull
	private BigDecimal price;
}
