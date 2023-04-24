package com.shop.bike.vm;

import com.shop.bike.entity.enumeration.MediaType;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.malu.base.media.domain.Media} entity.
 */
@Data
public class UploadVM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -812567588752641290L;

	private String path;

	private MediaType mediaType;

	private String originalName;

}
