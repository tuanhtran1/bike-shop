package com.shop.bike.consumer.vm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.bike.admin.vm.ProductAttributeVM;
import com.shop.bike.entity.TradingProduct;
import com.shop.bike.utils.JsonConverter;
import com.shop.bike.vm.TradingProductBaseVM;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

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
	
	@JsonIgnore
	private String tradingProductCache;
	
	private List<ProductAttributeVM> attributes;
	
	public void setTradingProductCache(String tradingProductCache) {
		this.tradingProductCache = tradingProductCache;
		TradingProductBaseVM tradingProductBaseVM = JsonConverter.toObject(this.tradingProductCache, TradingProductBaseVM.class);
		this.attributes = tradingProductBaseVM.getAttributes();
	}
	
}
