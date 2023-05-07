package com.shop.bike.service;

import com.shop.bike.vm.BrandVM;

import java.util.List;

public interface BrandService {

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
	List<BrandVM> getListOption();
}