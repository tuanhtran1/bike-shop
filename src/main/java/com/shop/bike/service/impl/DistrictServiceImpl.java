package com.shop.bike.service.impl;

import com.shop.bike.entity.District;
import com.shop.bike.repository.DistrictRepository;
import com.shop.bike.service.DistrictService;
import com.shop.bike.vm.DistrictVM;
import com.shop.bike.vm.mapper.DistrictVMMapper;
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
public class DistrictServiceImpl implements DistrictService {
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private DistrictVMMapper districtVMMapper;
	
	/*************************************************************
	 *
	 * @decription: get district with province id
	 *
	 * @param: provinceId, pageable
	 * @return: page
	 *
	 * @date: 06/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 *************************************************************/
	@Override
	@Transactional(readOnly = true)
	public Page<DistrictVM> findAll(Long provinceId, Pageable pageable) {
		log.debug("Request to get all Districts");
		return districtRepository.findAllByProvinceId(provinceId, pageable).map(districtVMMapper::toDto);
	}
	
	@Override
	public Optional<District> findOneById(Long districtId) {
		return districtRepository.findById(districtId);
	}
}
