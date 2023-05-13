package com.shop.bike.service.impl;

import com.shop.bike.entity.Wards;
import com.shop.bike.repository.WardsRepository;
import com.shop.bike.service.WardsService;
import com.shop.bike.vm.WardsVM;
import com.shop.bike.vm.mapper.WardsVMMapper;
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
public class WardsServiceImpl implements WardsService {
	
	@Autowired
	private WardsRepository wardsRepository;
	
	@Autowired
	private WardsVMMapper wardsVMMapper;
	
	
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
	@Override
	public Page<WardsVM> findAll(Long districtId, Pageable pageable) {
		log.debug("Request to get all Wards: {}", districtId);
		return wardsRepository.findAllByDistrictId(districtId, pageable).map(wardsVMMapper::toDto);
	}
	
	@Override
	public Optional<Wards> findOneById(Long wardsId) {
		return wardsRepository.findById(wardsId);
	}
	
}
