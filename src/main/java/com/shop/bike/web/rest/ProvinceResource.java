package com.shop.bike.web.rest;

import com.shop.bike.service.ProvinceService;
import com.shop.bike.utils.PaginationUtil;
import com.shop.bike.vm.ProvinceVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class ProvinceResource {

    private final Logger log = LoggerFactory.getLogger(ProvinceResource.class);

    private final ProvinceService provinceService;
	
	public ProvinceResource(ProvinceService provinceService) {
		this.provinceService = provinceService;
	}
	@GetMapping("/provinces/{countryId}")
    public ResponseEntity<List<ProvinceVM>> getAllProvinces(@PathVariable(name = "countryId", required = false)Long countryId,
															Pageable pageable) {
        log.debug("REST request to get a page of Provinces");
        Page<ProvinceVM> page = provinceService.findAll(countryId, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
