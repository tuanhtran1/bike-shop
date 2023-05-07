package com.shop.bike.service.impl;

import com.shop.bike.entity.enumeration.ActionStatus;
import com.shop.bike.repository.BrandRepository;
import com.shop.bike.service.BrandService;
import com.shop.bike.vm.BrandVM;
import com.shop.bike.vm.mapper.BrandVMMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Primary
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private BrandVMMapper vmMapper;
	
	/*************************************************************
	 *
	 * @decription: get option list brand
	 *
	 * @param:
	 * @return:
	 *
	 * @date: 07/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 **************************************************************/
	@Override
	public List<BrandVM> getListOption() {
		return brandRepository.findAllByStatusNot(ActionStatus.DELETED)
				.stream().map(vmMapper::toDto).collect(Collectors.toList());
	}
}
