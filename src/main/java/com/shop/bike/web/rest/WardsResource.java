package com.shop.bike.web.rest;


import com.shop.bike.service.WardsService;
import com.shop.bike.utils.PaginationUtil;
import com.shop.bike.vm.WardsVM;
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
public class WardsResource {

    private final Logger log = LoggerFactory.getLogger(WardsResource.class);

    @Autowired
    private WardsService wardsService;
   
    @GetMapping("/wards/{districtId}")
    public ResponseEntity<List<WardsVM>> getAllWards(@PathVariable("districtId")Long districtId, Pageable pageable) {
        log.debug("REST request to get a page of Wards");
        Page<WardsVM> page = wardsService.findAll(districtId, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
