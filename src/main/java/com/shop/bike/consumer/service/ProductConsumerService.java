package com.shop.bike.consumer.service;

import com.shop.bike.admin.dto.ProductAdminDTO;
import com.shop.bike.admin.dto.ProductFilterDTO;
import com.shop.bike.admin.vm.ProductAdminVM;
import com.shop.bike.consumer.vm.ProductBaseConsumerVM;
import com.shop.bike.consumer.vm.ProductConsumerVM;
import com.shop.bike.entity.Product;
import com.shop.bike.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductConsumerService extends ProductService {
	
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
	
	Page<ProductBaseConsumerVM> findAll(String keyword, Long brandId, Boolean isHaveFlashSale, Pageable pageable);
	
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
	Optional<ProductConsumerVM> findOneByConsumer(Long id);
}
