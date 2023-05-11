package com.shop.bike.service;

import com.shop.bike.entity.Product;

public interface ProductService {
	
	void increaseAmountOrder(Product product, Integer quantity);
}
