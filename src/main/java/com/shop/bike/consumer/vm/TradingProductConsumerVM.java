package com.shop.bike.consumer.vm;

import com.shop.bike.admin.vm.ProductAttributeVM;
import com.shop.bike.entity.enumeration.ActionStatus;
import com.shop.bike.entity.enumeration.ApproveStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class TradingProductConsumerVM {
	
	private Long id;
	
	private String name;
	
	private BigDecimal price;
	
	private BigDecimal salePrice;
	
	private BigDecimal originalPrice;
	
	private Integer stockQuantity;
	
	private Integer lowStockThreshold;
	
	private ActionStatus status;
	
	private ApproveStatus approveStatus;
	
	private Long brandId;
	
	private List<ProductAttributeVM> attributes;
	
	private String note;
	
	private Instant createdDate;
	
	private Instant lastModifiedDate;
}
