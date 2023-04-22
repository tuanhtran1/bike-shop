package com.shop.bike.admin.dto;

import com.shop.bike.entity.enumeration.ActionStatus;
import lombok.Data;

import java.time.Instant;

@Data
public class ProductFilterDTO {
	
	private String keyword;
	
	private String code;
	
	private String oldCode;
	
	private String upcCode;
	
	private String name;
	
	private String shortName;
	
	private String note;
	
	private Long brandId;
	
	private String brandName;
	
	private Instant createdDateFrom;
	
	private Instant createdDateTo;
	
	private Instant lastModifiedDateFrom;
	
	private Instant lastModifiedDateTo;
	
	private ActionStatus productStatus;

	private String origin;
}
