package com.shop.bike.consumer.vm;

import com.shop.bike.entity.enumeration.ActionStatus;
import com.shop.bike.entity.enumeration.ApproveStatus;
import com.shop.bike.vm.ProductVM;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductConsumerVM extends ProductVM {
	
	private Set<TradingProductConsumerVM> tradingProducts;
	
	public void setTradingProducts(Set<TradingProductConsumerVM> tradingProducts) {
		this.tradingProducts = tradingProducts.stream()
				.filter(t -> (t.getStatus() == ActionStatus.ACTIVATED && t.getApproveStatus() == ApproveStatus.APPROVED && t.getStockQuantity() > 0))
				.collect(Collectors.toSet());
	}
	
}
