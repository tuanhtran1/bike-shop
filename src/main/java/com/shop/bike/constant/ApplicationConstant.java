package com.shop.bike.constant;

public class ApplicationConstant {

	private ApplicationConstant() {
		throw new IllegalStateException("Constant class");
	}

	// Inject bean
	public static final String EXTEND = "extend";
	public static final String CONSUMER = "consumer";
	public static final String ADMIN = "admin";

	// Language default
	public static final String LANGUAGE_DEFAULT = "en";
	
	//prefix version
	public static final String PREFIX_VERSION = "v";
	
	//cacheName
	public static final String CACHE_I18_CONSUMER="cache_i18_consumer";
}
