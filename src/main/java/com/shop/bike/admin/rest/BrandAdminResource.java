package com.shop.bike.admin.rest;

import com.shop.bike.admin.dto.BrandAdminDTO;
import com.shop.bike.admin.service.BrandAdminService;
import com.shop.bike.admin.vm.BrandAdminVM;
import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.vm.BrandVM;
import com.shop.bike.web.rest.errors.BadRequestAlertException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/admin")
@Slf4j
public class BrandAdminResource {
	
	private static final String ENTITY_NAME = "brand";
	
	@Autowired
	@Qualifier(ApplicationConstant.ADMIN)
	private BrandAdminService brandAdminService;
	
	
	@GetMapping("/brands/option")
	public ResponseEntity<List<BrandVM>> findAll() {
		log.debug("REST request to get all Brand Option ");
		List<BrandVM> listOption = brandAdminService.getListOptionAdmin();
		return ResponseEntity.ok().body(listOption);
	}
	
	@GetMapping("/brand/{id}")
	public ResponseEntity<BrandAdminVM> getProduct(@PathVariable Long id) {
		log.debug("REST request to get Product : {}", id);
		Optional<BrandAdminVM> brandAdminVM = brandAdminService.findOneByAdmin(id);
		return brandAdminVM.map(response -> ResponseEntity.ok().body(response))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/brand")
	public ResponseEntity<BrandAdminVM> createBrand(@Valid @RequestBody BrandAdminDTO brandAdminDTO) throws URISyntaxException {
		log.debug("REST request to save Brand : {}", brandAdminDTO);
		if (brandAdminDTO.getId() != null) {
			throw new BadRequestAlertException("A new brand cannot already have an ID", ENTITY_NAME, "idexists");
		}
		BrandAdminVM result = brandAdminService.saveBrandByAdmin(brandAdminDTO);
		return ResponseEntity.created(new URI("/api/product/" + result.getId())).body(result);
	}
	
	@PutMapping("/brand/{id}")
	public ResponseEntity<BrandAdminVM> updateBrand(@PathVariable(value = "id", required = false) final Long id,
														@RequestBody BrandAdminDTO brandAdminDTO
	) throws URISyntaxException {
		log.debug("REST request to update Brand : {}, {}", id, brandAdminDTO);
		if (brandAdminDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		if (!Objects.equals(id, brandAdminDTO.getId())) {
			throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
		}
		BrandAdminVM result = brandAdminService.saveBrandByAdmin(brandAdminDTO);
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping("/brand/{id}")
	public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
		log.debug("REST request to delete Brand : {}", id);
		brandAdminService.deleteBrandByAdmin(id);
		return ResponseEntity.noContent().build();
	}
}
