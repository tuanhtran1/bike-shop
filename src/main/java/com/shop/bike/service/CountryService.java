package com.shop.bike.service;

import com.shop.bike.entity.Country;
import com.shop.bike.vm.CountryVM;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CountryService {
	
	/*************************************************************
	 *
	 * @decription: get country list
	 *
	 * @param:
	 * @return:
	 *
	 * @date: 06/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	**************************************************************/
	List<Country> findAll(String keyword);
	
	Optional<Country> findOneById(Long countryId);
}