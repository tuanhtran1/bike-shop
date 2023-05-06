package com.shop.bike.vm;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CountryVM implements Serializable {
	
	private static final long serialVersionUID = 4324337068074677108L;

	private Long id;

	private String code;

	private String name;
	
	private String phone;
	
	private String nativeName;

}
