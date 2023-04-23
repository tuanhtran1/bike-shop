/**
 * 
 */
package com.shop.bike.service.dto;

import com.shop.bike.entity.enumeration.MediaType;
import lombok.Data;

import java.io.Serializable;

@Data
public class MediaDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2810974975221542085L;

	private String url;

	private MediaType type;
	
}
