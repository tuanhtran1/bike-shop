package com.shop.bike.utils;

import org.springframework.core.io.UrlResource;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;

public class ResourceSerializable extends UrlResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3418529337170745636L;

	public ResourceSerializable(URI uri) throws MalformedURLException {
		super(uri);
	}

	public ResourceSerializable(String string) throws MalformedURLException {
		super(string);
	}


}
