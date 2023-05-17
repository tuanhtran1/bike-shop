package com.shop.bike.service;

import java.util.Map;

public interface OtpService {
	
	void sendOtp(String username);
	
	Map<String, String> validateOtp(String otp, String username);
}
