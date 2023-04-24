package com.shop.bike.vm;

import com.shop.bike.entity.enumeration.MediaType;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.malu.base.media.domain.Media} entity.
 */
@Data
public class MediaVM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4251085245487912235L;

	private Long id;

	private String name;

	private String path;

	private MediaType mediaType;

	private String originalName;

	private BigDecimal fileSize;

}
