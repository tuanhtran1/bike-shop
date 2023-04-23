package com.shop.bike.consumer.vm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.bike.utils.JsonConverter;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MyOrderDetailsConsumerVM {

    private Long id;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal discount;

    private BigDecimal total;

    private Long productId;

    private String productName;

    @JsonIgnore
    private String images;

    private Long tradingProductId;
	
    @JsonProperty("images")
	public Object getImagesObject() {
		return JsonConverter.toObject(this.images, Object.class);
	}

}
