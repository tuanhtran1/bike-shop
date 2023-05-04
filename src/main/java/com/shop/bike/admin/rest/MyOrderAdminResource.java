package com.shop.bike.admin.rest;

import com.shop.bike.admin.dto.MyOrderFilterDTO;
import com.shop.bike.admin.service.MyOrderAdminService;
import com.shop.bike.admin.vm.MyOrderAdminVM;
import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.utils.PaginationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping("/api/v1/admin")
@Slf4j
public class MyOrderAdminResource {
	
	private static final String ENTITY_NAME = "my_order";
	
	@Autowired
	@Qualifier(ApplicationConstant.ADMIN)
	private MyOrderAdminService myOrderAdminService;
	
	@GetMapping("/orders")
	public ResponseEntity<List<MyOrderAdminVM>> findAll(MyOrderFilterDTO filters, @SortDefault.SortDefaults({
			@SortDefault(sort = "lastModifiedDate", direction = Sort.Direction.DESC) }) Pageable pageable) {
		Page<MyOrderAdminVM> page = myOrderAdminService.findAllByAdmin(filters, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}
}
