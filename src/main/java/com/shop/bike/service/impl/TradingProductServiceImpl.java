package com.shop.bike.service.impl;

import com.shop.bike.entity.TradingProduct;
import com.shop.bike.entity.enumeration.ActionStatus;
import com.shop.bike.entity.enumeration.ErrorEnum;
import com.shop.bike.repository.TradingProductRepository;
import com.shop.bike.service.ProductService;
import com.shop.bike.service.TradingProductService;
import com.shop.bike.vm.TradingProductBaseVM;
import com.shop.bike.vm.mapper.TradingProductBaseVMMapper;
import com.shop.bike.web.rest.errors.BadRequestAlertException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Primary
@Slf4j
public class TradingProductServiceImpl implements TradingProductService {
	
	@Autowired
	private TradingProductRepository tradingProductRepository;
	
	@Autowired
	private TradingProductBaseVMMapper tradingProductBaseVMMapper;
	
	@Override
	@Transactional(readOnly = true)
	public TradingProductBaseVM findOneById(Long id) {
		return tradingProductBaseVMMapper.toDto(tradingProductRepository.findById(id)
				.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.TRADING_PRODUCT_NOT_FOUND)));
	}
	
	@Override
	public void saveStockQuantity(Long id, Integer quantity) {
		TradingProduct tradingProduct = tradingProductRepository.findById(id)
				.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.TRADING_PRODUCT_NOT_FOUND));
		if(tradingProduct.getStockQuantity() >= quantity){
			tradingProduct.setStockQuantity(tradingProduct.getStockQuantity() - quantity);
			tradingProductRepository.save(tradingProduct);
		}
		else throw new BadRequestAlertException(ErrorEnum.STOCK_QUANTITY_NOT_ENOUGH);
	}
}
