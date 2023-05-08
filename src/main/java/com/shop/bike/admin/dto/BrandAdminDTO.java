package com.shop.bike.admin.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class BrandAdminDTO {
	
	private Long id;
	
	@NotNull
	@NotEmpty
	private String name;
	
	private String shortName;
	
	private String description;
	
}
