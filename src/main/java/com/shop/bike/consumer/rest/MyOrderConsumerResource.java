package com.shop.bike.consumer.rest;

import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.consumer.dto.CreateOrderDTO;
import com.shop.bike.consumer.service.MyOrderConsumerService;
import com.shop.bike.consumer.vm.MyOrderConsumerVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;

@Controller
@RequestMapping("/api/v1/consumer")
@Slf4j
public class MyOrderConsumerResource {
	
	private static final String ENTITY_NAME = "my_order";
	
	@Autowired
	@Qualifier(ApplicationConstant.CONSUMER)
	private MyOrderConsumerService myOrderConsumerService;
	
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
