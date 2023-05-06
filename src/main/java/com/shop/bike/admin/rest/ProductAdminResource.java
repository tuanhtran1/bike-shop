package com.shop.bike.admin.rest;

import com.shop.bike.admin.dto.ProductAdminDTO;
import com.shop.bike.admin.dto.ProductFilterDTO;
import com.shop.bike.admin.service.ProductAdminService;
import com.shop.bike.admin.vm.ProductAdminVM;
import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.utils.PaginationUtil;
import com.shop.bike.web.rest.errors.BadRequestAlertException;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/admin")
@Slf4j
public class ProductAdminResource {
	
	private static final String ENTITY_NAME = "product";
	
	@Autowired
	@Qualifier(ApplicationConstant.ADMIN)
	private ProductAdminService productAdminService;
	
	@GetMapping("/products")
	public ResponseEntity<List<ProductAdminVM>> findAll(ProductFilterDTO filters, Pageable pageable) {
		Page<ProductAdminVM> page = productAdminService.findAllProductByAdmin(filters, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<ProductAdminVM> getProduct(@PathVariable Long id) {
		log.debug("REST request to get Product : {}", id);
		Optional<ProductAdminVM> productVendorVM = productAdminService.findOneByAdmin(id);
		return productVendorVM.map(response -> ResponseEntity.ok().body(response))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/product")
	public ResponseEntity<ProductAdminVM> createProduct(@Valid @RequestBody ProductAdminDTO productAdminDTO) throws URISyntaxException {
		log.debug("REST request to save Product : {}", productAdminDTO);
		if (productAdminDTO.getId() != null) {
			throw new BadRequestAlertException("A new product cannot already have an ID", ENTITY_NAME, "idexists");
		}
		ProductAdminVM result = productAdminService.saveProductByAdmin(productAdminDTO);
		return ResponseEntity.created(new URI("/api/product/" + result.getId())).body(result);
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<ProductAdminVM> updateProduct(@PathVariable(value = "id", required = false) final Long id,
														 @RequestBody ProductAdminDTO productVendorDTO
	) throws URISyntaxException {
		log.debug("REST request to update Product : {}, {}", id, productVendorDTO);
		if (productVendorDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		if (!Objects.equals(id, productVendorDTO.getId())) {
			throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
		}
		ProductAdminVM result = productAdminService.saveProductByAdmin(productVendorDTO);
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		log.debug("REST request to delete Product : {}", id);
		productAdminService.deleteProductByAdmin(id);
		return ResponseEntity.noContent().build();
	}
}
