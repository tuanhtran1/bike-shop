package com.shop.bike.service.impl;

import com.shop.bike.entity.Country;
import com.shop.bike.repository.CountryRepository;
import com.shop.bike.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Primary
@Slf4j
public class CountryServiceImpl implements CountryService {
	
	@Autowired
	private CountryRepository countryRepository;
	
	/*************************************************************
	 *
	 * @decription: get country list
	 *
	 * @param:
	 * @return:
	 *
	 * @date: 06/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 *************************************************************/
	@Override
	@Transactional(readOnly = true)
	public List<Country> findAll(String keyword) {
		log.debug("Request to get all Countries: {}", keyword);
		return countryRepository.findAll(keyword);
	}
	
	@Override
	public Optional<Country> findOneById(Long countryId) {
		return countryRepository.findById(countryId);
	}
	
	
}
