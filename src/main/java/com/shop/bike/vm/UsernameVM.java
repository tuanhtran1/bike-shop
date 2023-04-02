package com.shop.bike.vm;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsernameVM {
	
	@NotBlank
	@Size(min = 1, max = 50)
	private String username;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
}
