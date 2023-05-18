package com.shop.bike.consumer.dto;

import com.shop.bike.config.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
public class RegisterConsumerDTO {
	
	@NotNull
	private String activatedKey;
	
	@NotNull
	private String username;
	
	@NotNull
	@Size(min = 4, max = 100)
	private String password;
	
	@Pattern(regexp = Constants.PHONE_REGEX)
	private String phone;
	
	@NotNull
	private String name;
	
	private String email;
}
