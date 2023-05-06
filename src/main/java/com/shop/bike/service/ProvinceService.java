package com.shop.bike.service;

import com.shop.bike.entity.Province;
import com.shop.bike.vm.ProvinceVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProvinceService {
	
	/*************************************************************
	 *
	 * @decription: find all
	 *
	 * @param: countryId
	 * @return: page
	 *
	 * @date: 06/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	**************************************************************/
	Page<ProvinceVM> findAll(Long countryId, Pageable pageable);
	
	Optional<Province> findOneById(Long provinceId);
}