package com.shop.bike.service.dto;

import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class OtpDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4354652698729642089L;

	private Long id;

	private Integer resendOtp = 0;

	@Size(max = 10)
	private String otp;

	private Integer incorrectOtp = 0;

	@Size(max = 100)
	private String userName;

	@Size(max = 50)
	private String activeKey;

}
