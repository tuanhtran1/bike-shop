package com.shop.bike.web.rest;


import com.shop.bike.service.CountryService;
import com.shop.bike.vm.CountryVM;
import com.shop.bike.vm.mapper.CountryVMMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class CountryResource {

	private final Logger log = LoggerFactory.getLogger(CountryResource.class);

	private final CountryService countryService;

	private final CountryVMMapper countryVMMapper;
	
	public CountryResource(CountryService countryService, CountryVMMapper countryVMMapper) {
		this.countryService = countryService;
		this.countryVMMapper = countryVMMapper;
	}
	
	@GetMapping("/countries/list")
	public ResponseEntity<List<CountryVM>> getAllCountries(
			@RequestParam(name = "keyword", required = false) String keyword) {
		log.debug("REST request to get a page of Countries");
		List<CountryVM> list = countryService.findAll(keyword).stream().map(countryVMMapper::toDto)
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}
}
