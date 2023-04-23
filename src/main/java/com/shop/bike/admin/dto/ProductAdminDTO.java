package com.shop.bike.admin.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.bike.service.dto.BrandDTO;
import com.shop.bike.utils.JsonConverter;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class ProductAdminDTO {

	private Long id;

	private String oldCode;

	@NotNull
	private String name;

	private String shortName;

	@JsonIgnore
	private String images;

	@JsonProperty("images")
	private Object imagesObject;

	@Size(max = 10000)
	private String description;

	@Size(max = 100000)
	private String details;

	@Size(max = 10000)
	private String specifications;

	private String origin;

	@NotNull
	private Long brandId;
	
	public void setImagesObject(Object imagesObject) {
		this.imagesObject = imagesObject;
		this.images = JsonConverter.toJson(imagesObject);
	}
}
