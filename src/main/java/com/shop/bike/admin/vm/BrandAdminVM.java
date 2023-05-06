package com.shop.bike.admin.vm;

import com.shop.bike.entity.enumeration.ActionStatus;
import lombok.Data;

@Data
public class BrandAdminVM {
	
	private Long id;
	
	
	private String name;
	
	
	private String shortName;
	
	
	private ActionStatus status;
	
	
	private String description;
}
