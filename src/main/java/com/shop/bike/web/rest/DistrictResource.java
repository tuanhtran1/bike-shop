package com.shop.bike.web.rest;


import com.shop.bike.service.DistrictService;
import com.shop.bike.utils.PaginationUtil;
import com.shop.bike.vm.DistrictVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class DistrictResource {

    private final Logger log = LoggerFactory.getLogger(DistrictResource.class);
	
    @Autowired
    private DistrictService districtService;
	
    @GetMapping("/districts/{provinceId}")
    public ResponseEntity<List<DistrictVM>> getAllDistricts(@PathVariable("provinceId")Long provinceId, Pageable pageable) {
        log.debug("REST request to get a page of Districts: {}", provinceId);
        Page<DistrictVM> page = districtService.findAll(provinceId, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
