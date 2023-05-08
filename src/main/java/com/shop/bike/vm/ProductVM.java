package com.shop.bike.vm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.bike.entity.enumeration.ActionStatus;
import com.shop.bike.utils.JsonConverter;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class ProductVM {
	
	private Long id;
	
	private String name;
	
	private String code;
	
	private String oldCode;
	
	private ActionStatus productStatus;
	
	private String description;
	
	private String details;
	
	@JsonIgnore
	private String images;
	
	@JsonProperty("images")
	private Object imageObjects;
	private String specifications;
	
	private String approver;
	
	private String origin;
	
	private BrandVM brand;
	
	private Instant createdDate;
	
	private Instant lastModifiedDate;
	
	private BigDecimal maxPrice;
	
	private BigDecimal minPrice;
	
	public void setImages(String images) {
		this.images = images;
		this.imageObjects = JsonConverter.toObjectArray(images);
	}
	
}
