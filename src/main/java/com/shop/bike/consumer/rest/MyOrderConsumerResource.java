package com.shop.bike.consumer.rest;

import com.shop.bike.admin.dto.MyOrderFilterDTO;
import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.consumer.dto.CreateOrderDTO;
import com.shop.bike.consumer.service.MyOrderConsumerService;
import com.shop.bike.consumer.vm.MyOrderConsumerVM;
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
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/consumer")
@Slf4j
public class MyOrderConsumerResource {
	
	private static final String ENTITY_NAME = "my_order";
	
	@Autowired
	@Qualifier(ApplicationConstant.CONSUMER)
	private MyOrderConsumerService myOrderConsumerService;
	
	@GetMapping("/my-orders")
	public ResponseEntity<List<MyOrderConsumerVM>> getAllOrders(
			MyOrderFilterDTO filters, Pageable pageable) {
		log.debug("REST request to get a page of Orders");
		Page<MyOrderConsumerVM> page = myOrderConsumerService.findAllOrders(filters, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}
	
	@GetMapping("/my-orders/{id}")
	public ResponseEntity<MyOrderConsumerVM> getOrder(@PathVariable("id")Long id, Pageable pageable) {
		log.debug("REST request to get a page of Orders");
		Optional<MyOrderConsumerVM> myOrderConsumerVM = myOrderConsumerService.findById(id);
		return myOrderConsumerVM.map(response -> ResponseEntity.ok().body(response))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/my-orders")
	public ResponseEntity<MyOrderConsumerVM> createMyOrder(@Valid @RequestBody CreateOrderDTO dto) throws URISyntaxException {
		log.debug("REST request to save MyOrder : {}", dto);
		MyOrderConsumerVM result = myOrderConsumerService.createOrderByConsumer(dto);
		return ResponseEntity.ok(result);
	}
	
	@PutMapping("/orders/cancel/{id}")
	public ResponseEntity<Void	> cancelOrder(@PathVariable Long id, @RequestParam(name = "note", required = false)String note) {
		log.debug("REST request to get MyOrder : {}", id);
		myOrderConsumerService.cancel(id, note);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/orders/confirm/payment-cash/{id}")
	public ResponseEntity<Void> paymentCash(@PathVariable Long id ) {
		log.debug("REST request to get MyOrder : {}", id);
		myOrderConsumerService.paymentCash(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/orders/confirm/payment-trade/{id}")
	public ResponseEntity<Void> paymentTrade(@PathVariable Long id ) {
		log.debug("REST request to get MyOrder : {}", id);
		myOrderConsumerService.paymentTrade(id);
		return ResponseEntity.noContent().build();
	}
}
