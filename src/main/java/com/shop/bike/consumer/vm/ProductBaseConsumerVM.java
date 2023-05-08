package com.shop.bike.consumer.vm;

import com.shop.bike.vm.ProductVM;
import lombok.Getter;
import lombok.Setter;

/**
 * A VM for the {@link com.malu.base.product.domain.Product} entity.
 */
@Getter
@Setter
public class ProductBaseConsumerVM extends ProductVM {
	
	private Boolean isHaveFlashSale;
	
	private Boolean isSoldOut;
	
}
