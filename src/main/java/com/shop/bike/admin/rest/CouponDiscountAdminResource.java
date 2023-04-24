package com.shop.bike.admin.rest;

import com.shop.bike.admin.dto.CouponDiscountAdminDTO;
import com.shop.bike.admin.dto.CouponDiscountFiltersDTO;
import com.shop.bike.admin.dto.ProductAdminDTO;
import com.shop.bike.admin.dto.ProductFilterDTO;
import com.shop.bike.admin.service.CouponDiscountAdminService;
import com.shop.bike.admin.service.ProductAdminService;
import com.shop.bike.admin.vm.ProductAdminVM;
import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.utils.PaginationUtil;
import com.shop.bike.vm.CouponDiscountVM;
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
public class CouponDiscountAdminResource {
	
	private static final String ENTITY_NAME = "coupon_discount";
	
	@Autowired
	@Qualifier(ApplicationConstant.ADMIN)
	private CouponDiscountAdminService couponDiscountAdminService;
	
	@GetMapping("/coupon-discounts")
	public ResponseEntity<List<CouponDiscountVM>> findAll(CouponDiscountFiltersDTO filters, Pageable pageable) {
		Page<CouponDiscountVM> page = couponDiscountAdminService.findAllByAdmin(filters, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	@GetMapping("/coupon-discount/{id}")
	public ResponseEntity<CouponDiscountVM> getDetailCoupon(@PathVariable("id") Long id) {
		log.debug("REST request to get a page of promotion");
		CouponDiscountVM couponDiscountVM = couponDiscountAdminService.getDetailByAdmin(id);
		return ResponseEntity.ok().body(couponDiscountVM);
	}

	@PostMapping("/coupon-discount")
	public ResponseEntity<CouponDiscountVM> createPromotionCoupon(@Valid @RequestBody CouponDiscountAdminDTO couponDiscountDTO) throws URISyntaxException {
		log.debug("REST request to save Promotion : {}", couponDiscountDTO);
		if (couponDiscountDTO.getId() != null) {
			throw new BadRequestAlertException("A new promotion cannot already have an ID", ENTITY_NAME, "idexists");
		}
		int check = couponDiscountDTO.getEndDate().compareTo(couponDiscountDTO.getStartDate());
		if (check <= 0) {
			throw new BadRequestAlertException("End day less than start date", ENTITY_NAME, "invalid");
		}
		CouponDiscountVM result = couponDiscountAdminService.saveCouponByAdmin(couponDiscountDTO);
		return ResponseEntity.created(new URI("/api/promotion/coupon" + result.getId())).body(result);
	}
	
	@PutMapping("/coupon-discount/{id}")
	public ResponseEntity<CouponDiscountVM> updateCouponDiscount(@PathVariable(value = "id", required = false) final Long id,
															 @Valid @RequestBody CouponDiscountAdminDTO couponDiscountAdminDTO
	) throws URISyntaxException {
		log.debug("REST request to update Promotion : {}, {}", id, couponDiscountAdminDTO);
		if (couponDiscountAdminDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		if (!Objects.equals(id, couponDiscountAdminDTO.getId())) {
			throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
		}
		if(couponDiscountAdminDTO.getEndDate() != null){
			int check = couponDiscountAdminDTO.getEndDate().compareTo(couponDiscountAdminDTO.getStartDate());
			if (check <= 0) {
				throw new BadRequestAlertException("End day less than start date", ENTITY_NAME, "invalid");
			}
		}
		CouponDiscountVM result = couponDiscountAdminService.saveCouponByAdmin(couponDiscountAdminDTO);
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping("/coupon-discount/{id}")
	public ResponseEntity<Void> deleteCouponDiscount(@PathVariable Long id) {
		log.debug("REST request to delete Promotion : {}", id);
		couponDiscountAdminService.deleteCouponByAdmin(id);
		return ResponseEntity.noContent().build();
	}
}
