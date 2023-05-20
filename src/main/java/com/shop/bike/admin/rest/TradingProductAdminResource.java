package com.shop.bike.admin.rest;


import com.shop.bike.admin.dto.TradingProductAdminDTO;
import com.shop.bike.admin.dto.TradingProductFilterDTO;
import com.shop.bike.admin.dto.WareHouseDTO;
import com.shop.bike.admin.service.TradingProductAdminService;
import com.shop.bike.admin.vm.ProductAdminVM;
import com.shop.bike.admin.vm.TradingProductAdminVM;
import com.shop.bike.constant.ApplicationConstant;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/admin")
@Slf4j
public class TradingProductAdminResource {
	
	private static final String ENTITY_NAME = "trading_product";
	
	@Autowired
	@Qualifier(ApplicationConstant.ADMIN)
	private TradingProductAdminService tradingProductAdminService;
	
	@GetMapping("/trading-product/{id}")
	public ResponseEntity<TradingProductAdminVM> getProduct(@PathVariable Long id) {
		log.debug("REST request to get Product : {}", id);
		Optional<TradingProductAdminVM> productVendorVM = tradingProductAdminService.findOneByAdmin(id);
		return productVendorVM.map(response -> ResponseEntity.ok().body(response))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/trading-products")
	public ResponseEntity<List<TradingProductAdminVM>> getAllTradingProduct(TradingProductFilterDTO filters, Pageable pageable) {
		Page<TradingProductAdminVM> page = tradingProductAdminService.findAllByAdmin(filters, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	@PostMapping("/trading-product")
	public ResponseEntity<TradingProductAdminVM> createTradingProduct(@Valid @RequestBody TradingProductAdminDTO dto) {
		return ResponseEntity.ok(tradingProductAdminService.createTradingProductByAdmin(dto));

	}
	
	@PutMapping("/trading-product")
	public ResponseEntity<TradingProductAdminVM> updateTradingProduct(@RequestBody TradingProductAdminDTO dto) {
		return ResponseEntity.ok(tradingProductAdminService.updateTradingProductByAdmin(dto));
	}

	@DeleteMapping("/trading-product/{id}")
	public ResponseEntity<Void> deleteTradingProduct(@PathVariable Long id) {
		tradingProductAdminService.deleteTradingProductByAdmin(id);
		return ResponseEntity.noContent().build();
	}
//
//	@PutMapping(path = "/trading-product/cancel/{id}")
//	@ResponseStatus(code = HttpStatus.NO_CONTENT)
//	public ResponseEntity<Void> cancelTradingProduct(@PathVariable("id") Long id) {
//		tradingProductVendorService.cancel(id);
//		return ResponseEntity.noContent().build();
//	}
	
	@PostMapping("/ware-house")
	public ResponseEntity<Void> createWareHouse(@Valid @RequestBody WareHouseDTO dto) {
		tradingProductAdminService.createWareHouse(dto);
		return ResponseEntity.noContent().build();
	}
}
