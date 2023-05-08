package com.shop.bike.consumer.vm;

import com.shop.bike.admin.vm.TradingProductAdminVM;
import com.shop.bike.vm.ProductVM;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * A VM for the {@link com.malu.base.product.domain.Product} entity.
 */
@Getter
@Setter
public class ProductConsumerVM extends ProductVM {
	
	private Set<TradingProductAdminVM> tradingProducts;
	
}
