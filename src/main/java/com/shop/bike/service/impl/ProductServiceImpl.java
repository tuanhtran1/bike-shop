package com.shop.bike.service.impl;

import com.shop.bike.entity.Product;
import com.shop.bike.repository.ProductRepository;
import com.shop.bike.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Primary
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void increaseAmountOrder(Product product, Integer quantity) {
		Integer currentAmount = product.getAmountOrders();
		product.setAmountOrders(currentAmount == null ? 0 : currentAmount + quantity);
		productRepository.save(product);
	}
}
