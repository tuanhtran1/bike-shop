package com.shop.bike.service.impl;

import com.shop.bike.entity.Province;
import com.shop.bike.repository.ProvinceRepository;
import com.shop.bike.service.ProvinceService;
import com.shop.bike.vm.ProvinceVM;
import com.shop.bike.vm.mapper.ProvinceVMMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Primary
@Slf4j
public class ProvinceServiceImpl implements ProvinceService {
	
	@Autowired
	private ProvinceRepository provinceRepository;
	
	@Autowired
	private ProvinceVMMapper provinceVMMapper;
	
	/*************************************************************
	 *
	 * @decription: find all
	 *
	 * @param: countryId
	 * @return: page
	 *
	 * @date: 06/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 *************************************************************/
	@Override
	@Transactional(readOnly = true)
	public Page<ProvinceVM> findAll(Long countryId, Pageable pageable) {
		log.debug("Request to get all Provinces");
		return provinceRepository.findAllByCountryIdOrStateId(countryId, pageable).map(provinceVMMapper::toDto);
	}
	
	@Override
	public Optional<Province> findOneById(Long provinceId) {
		return provinceRepository.findById(provinceId);
	}
}
