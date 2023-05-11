package com.shop.bike.admin.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Data
public class TradingProductAdminDTO {
	
	private Long id;
	
	private Long productId;
	
	private String name;
	
	@NotNull
	private BigDecimal price;
	
	private BigDecimal salePrice;
	
	private String description;
	
	private Integer stockQuantity;
	
	private Integer lowStockThreshold;
	
	private String note;
	
	private Long brandId;
	
	private BigDecimal originalPrice; //gia tham khao
	
	@NotNull
	private Set<ProductAttributeDTO> attributes;
	
}
