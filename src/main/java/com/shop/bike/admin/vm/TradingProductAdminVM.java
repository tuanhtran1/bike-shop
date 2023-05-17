package com.shop.bike.admin.vm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.bike.entity.enumeration.ActionStatus;
import com.shop.bike.utils.JsonConverter;
import com.shop.bike.vm.BrandVM;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class TradingProductAdminVM {
	
	private Long id;
	
	private Long productId;
	
	private String description;
	
	private String name;
	
	private BigDecimal price;
	
	private BigDecimal originalPrice;
	
	private Integer stockQuantity;
	
	private Integer lowStockThreshold;
	
	private ActionStatus status;
	
	private Long brandId;
	
	private List<ProductAttributeVM> attributes;
	
	private String note;
	
	private Instant createdDate;
	
	private Instant lastModifiedDate;
}
