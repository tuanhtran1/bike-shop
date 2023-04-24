package com.shop.bike.admin.service;

import com.shop.bike.admin.dto.CouponDiscountAdminDTO;
import com.shop.bike.admin.dto.CouponDiscountFiltersDTO;
import com.shop.bike.service.CouponDiscountService;
import com.shop.bike.vm.CouponDiscountVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CouponDiscountAdminService extends CouponDiscountService {
	
	/*************************************************************
	 *
	 * @decription: find all filters coupons
	 *
	 * @param:
	 * @return:
	 *
	 * @date: 24/04/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	**************************************************************/
	Page<CouponDiscountVM> findAllByAdmin(CouponDiscountFiltersDTO filters, Pageable pageable);
	
	/*************************************************************
	 *
	 * @decription: get detail coupon by admin
	 *
	 * @param: id
	 * @return: cm
	 *
	 * @date: 24/04/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	**************************************************************/
	CouponDiscountVM getDetailByAdmin(Long id);
	
	/*************************************************************
	 *
	 * @decription: create coupon discount
	 *
	 * @param: couponDiscountAdminDTO
	 * @return: vm
	 *
	 * @date: 24/04/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	**************************************************************/
	CouponDiscountVM saveCouponByAdmin(CouponDiscountAdminDTO couponDiscountAdminDTO);
	
	/*************************************************************
	 *
	 * @decription: delete coupon discount
	 *
	 * @param: id
	 * @return: none
	 *
	 * @date: 24/04/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	**************************************************************/
	void deleteCouponByAdmin(Long id);
}
