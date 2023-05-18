package com.shop.bike.service;

import com.shop.bike.vm.TradingProductBaseVM;

public interface TradingProductService {
	
	TradingProductBaseVM findOneById(Long id);
	
	void saveStockQuantity(Long id, Integer quantity);
}
