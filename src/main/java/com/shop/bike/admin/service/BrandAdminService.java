package com.shop.bike.admin.service;

import com.shop.bike.admin.dto.BrandAdminDTO;
import com.shop.bike.admin.vm.BrandAdminVM;
import com.shop.bike.service.BrandService;

import java.util.List;
import java.util.Optional;

public interface BrandAdminService extends BrandService {
	
	/*************************************************************
	 *
	 * @decription: get list option brand
	 *
	 * @param: none
	 * @return: list vm
	 *
	 * @date: 06/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	**************************************************************/
	List<BrandAdminVM> getListOption();
	
	/*************************************************************
	 *
	 * @decription: find one by admin
	 *
	 * @param: id
	 * @return: vm
	 *
	 * @date: 06/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	**************************************************************/
	Optional<BrandAdminVM> findOneByAdmin(Long id);
	
	/*************************************************************
	 *
	 * @decription: create brand by admin
	 *
	 * @param: dto
	 * @return: vm
	 *
	 * @date: 06/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	**************************************************************/
	BrandAdminVM saveBrandByAdmin(BrandAdminDTO dto);
	
	/*************************************************************
	 *
	 * @decription: delete brand by admin
	 *
	 * @param: id
	 * @return: none
	 *
	 * @date: 06/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	**************************************************************/
	void deleteBrandByAdmin(Long id);
}
