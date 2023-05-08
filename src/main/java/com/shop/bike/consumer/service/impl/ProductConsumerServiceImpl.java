package com.shop.bike.consumer.service.impl;

import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.consumer.repository.ProductConsumerRepository;
import com.shop.bike.consumer.service.ProductConsumerService;
import com.shop.bike.consumer.vm.ProductBaseConsumerVM;
import com.shop.bike.consumer.vm.ProductConsumerVM;
import com.shop.bike.consumer.vm.mapper.ProductBaseConsumerVMMapper;
import com.shop.bike.consumer.vm.mapper.ProductConsumerVMMapper;
import com.shop.bike.entity.enumeration.ActionStatus;
import com.shop.bike.service.impl.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Qualifier(ApplicationConstant.CONSUMER)
@Slf4j
public class ProductConsumerServiceImpl extends ProductServiceImpl implements ProductConsumerService {
	
	@Autowired
	@Qualifier(ApplicationConstant.CONSUMER)
	private ProductConsumerRepository productConsumerRepository;
	
	@Autowired
	private ProductBaseConsumerVMMapper productBaseConsumerVMMapper;
	
	@Autowired
	private ProductConsumerVMMapper productConsumerVMMapper;
	
	/*************************************************************
	 *
	 * @decription: find all product by consumer
	 *
	 * @param: keyword, brandId
	 * @return: pages
	 *
	 * @date: 07/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 **************************************************************/
	@Override
	@Transactional(readOnly = true)
	public Page<ProductBaseConsumerVM> findAll(String keyword, Long brandId, Pageable pageable) {
		return productConsumerRepository.findAllRunningProduct(keyword, brandId, pageable)
				.map(productBaseConsumerVMMapper::toDto);
	}
	
	/*************************************************************
	 *
	 * @decription: find one by consumer
	 *
	 * @param: id
	 * @return: vm
	 *
	 * @date: 07/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 **************************************************************/
	@Override
	@Transactional(readOnly = true)
	public Optional<ProductConsumerVM> findOneByConsumer(Long id) {
		return productConsumerRepository.findById(id)
				.filter(p -> ActionStatus.ACTIVATED.equals(p.getProductStatus()))
				.map(productConsumerVMMapper::toDto);
	}
}
