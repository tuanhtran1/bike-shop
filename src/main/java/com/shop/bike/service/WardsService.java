package com.shop.bike.service;

import com.shop.bike.entity.Wards;
import com.shop.bike.vm.WardsVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface WardsService {
	
	/*************************************************************
	 *
	 * @decription: find all
	 *
	 * @param: districtId
	 * @return:
	 *
	 * @date: 06/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	**************************************************************/
	
	Page<WardsVM> findAll(Long districtId, Pageable pageable);
	
	Optional<Wards> findOneById(Long wardsId);
}