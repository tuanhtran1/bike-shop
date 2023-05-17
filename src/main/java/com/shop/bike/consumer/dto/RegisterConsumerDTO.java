package com.shop.bike.consumer.dto;

import com.shop.bike.config.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Setter
@Getter
public class RegisterConsumerDTO {
	
	@NotNull
	private String activatedKey;
	
	@NotNull
	private String username;
	
	@NotNull
	private String password;
	
	@Pattern(regexp = Constants.PHONE_REGEX)
	private String phone;
	
	@NotNull
	private String name;
	
	private String email;
}
