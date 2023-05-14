package com.shop.bike.consumer.rest;

import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.consumer.service.AddressConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URISyntaxException;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/consumer")
@Slf4j
public class AddressConsumerResource {
	
	private static final String ENTITY_NAME = "address";
	
	@Autowired
	@Qualifier(ApplicationConstant.CONSUMER)
	private AddressConsumerService addressConsumerService;
	
	@GetMapping("/shipping-fee")
	public ResponseEntity<Map<String, Object>> getShippingFee(@RequestParam Double lat,
															  @RequestParam Double lng
															  ) throws URISyntaxException {
		log.debug("REST request to get shipping fee");
		Map<String, Object> result = addressConsumerService.calculateShippingAddress(lat, lng);
		return ResponseEntity.ok(result);
	}
}
