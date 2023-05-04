package com.shop.bike.admin.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class ProfileFilterDTO {
	
	private String keyword;
	
	private Long id;
	
	private String name;
	
	private String phone;
	
	private String email;
	
	private Integer status;
	
	private Instant createdDateFrom;
	
	private Instant createdDateTo;
	
}
