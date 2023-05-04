package com.shop.bike.vm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.bike.utils.JsonConverter;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MyOrderDetailsVM {
	
	private Long id;
	
	private Integer quantity;
	
	private BigDecimal unitPrice;
	
	private BigDecimal discount;
	
	private BigDecimal total;
	
	private Long productId;
	
	private String productName;
	
	@JsonIgnore
	private String images;
	
	private Long tradingProductId;
	
	@JsonProperty("images")
	public Object getImagesObject() {
		return JsonConverter.toObject(this.images, Object.class);
	}
}
