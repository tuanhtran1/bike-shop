package com.shop.bike.vm;

import com.shop.bike.admin.vm.ProductAttributeVM;
import com.shop.bike.entity.enumeration.ActionStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class TradingProductBaseVM {
	
	private Long id;
	
	private Long productId;
	
	private String description;
	
	private String images;
	
	private String name;
	
	private BigDecimal price;
	
	private BigDecimal originalPrice;
	
	private Integer stockQuantity;
	private Integer lowStockThreshold;
	
	private Long brandId;
	private List<ProductAttributeVM> attributes;
}
