package com.shop.bike.service;

import com.shop.bike.entity.District;
import com.shop.bike.vm.DistrictVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DistrictService {
	
	/*************************************************************
	 *
	 * @decription: get district with province id
	 *
	 * @param: provinceId, pageable
	 * @return: page
	 *
	 * @date: 06/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	**************************************************************/
	Page<DistrictVM> findAll(Long provinceId, Pageable pageable);
	
	Optional<District> findOneById(Long districtId);
}