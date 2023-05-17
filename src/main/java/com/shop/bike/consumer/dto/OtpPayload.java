package com.shop.bike.consumer.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class OtpPayload {
	
	@NotNull
	@NotBlank
	private String otp;
	
	public OtpPayload() {}

	public OtpPayload(@NotNull String otp) {
		super();
		this.otp = otp;
	}

}
