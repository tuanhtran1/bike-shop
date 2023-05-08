package com.shop.bike.consumer.rest;

import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.consumer.service.ProductConsumerService;
import com.shop.bike.consumer.vm.ProductBaseConsumerVM;
import com.shop.bike.consumer.vm.ProductConsumerVM;
import com.shop.bike.utils.PaginationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/consumer")
@Slf4j
public class ProductConsumerResource {
	
	private static final String ENTITY_NAME = "product";
	
	@Autowired
	@Qualifier(ApplicationConstant.CONSUMER)
	private ProductConsumerService productService;
	
	@GetMapping("/products")
	public ResponseEntity<List<ProductBaseConsumerVM>> getAllProducts(
			@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "brandId", required = false) Long brandId,
			Pageable pageable) {
		log.debug("REST request to get a page of Products");
		Page<ProductBaseConsumerVM> page = productService.findAll(keyword, brandId, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<ProductConsumerVM> getProduct(@PathVariable Long id) {
		log.debug("REST request to get Product : {}", id);
		Optional<ProductConsumerVM> productConsumerVM = productService.findOneByConsumer(id);
		return productConsumerVM.map(response -> ResponseEntity.ok().body(response))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}
