package com.shop.bike.constant;

import java.util.Arrays;
import java.util.List;

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
	
	public static final List<String> IMAGE_TYPE_ALLOW = Arrays.asList("jpeg", "jpe", "gif", "bmp", "png", "svg", "jpg",
			"ico");
	public static final List<String> AUDIO_TYPE_ALLOW = Arrays.asList("mp3", "wav", "m3u", "au", "m4a");
	public static final List<String> VIDEO_TYPE_ALLOW = Arrays.asList("mp4", "mov", "movie", "avi", "mpeg", "mpg");
	public static final List<String> DOCUMENT_TYPE_ALLOW = Arrays.asList("pdf", "doc", "docx", "txt", "xls", "xlsx");
}
