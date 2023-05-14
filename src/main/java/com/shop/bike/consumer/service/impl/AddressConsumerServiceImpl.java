package com.shop.bike.consumer.service.impl;

import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.consumer.dto.AddressDTO;
import com.shop.bike.consumer.repository.AddressConsumerRepository;
import com.shop.bike.consumer.service.AddressConsumerService;
import com.shop.bike.entity.*;
import com.shop.bike.entity.enumeration.ErrorEnum;
import com.shop.bike.service.CountryService;
import com.shop.bike.service.DistrictService;
import com.shop.bike.service.ProvinceService;
import com.shop.bike.service.WardsService;
import com.shop.bike.service.impl.AddressServiceImpl;
import com.shop.bike.utils.DistanceUtils;
import com.shop.bike.web.rest.errors.BadRequestAlertException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@Qualifier(ApplicationConstant.CONSUMER)
@Slf4j
public class AddressConsumerServiceImpl extends AddressServiceImpl implements AddressConsumerService {
	
	private final DistrictService districtService;
	
	private final ProvinceService provinceService;
	
	private final WardsService wardsService;
	
	private final CountryService countryService;
	
	@Autowired
	@Qualifier(ApplicationConstant.CONSUMER)
	private AddressConsumerRepository addressConsumerRepository;
	
	public AddressConsumerServiceImpl(DistrictService districtService, ProvinceService provinceService, WardsService wardsService, CountryService countryService) {
		this.districtService = districtService;
		this.provinceService = provinceService;
		this.wardsService = wardsService;
		this.countryService = countryService;
	}
	
	//this lat lng is location at 97 Man Thien Ho Chi Minh
	private final static double latitude_main = 10.848440;
	private final static double longitude_main = 106.787220;
	private final static BigDecimal pricePerKilometer = BigDecimal.valueOf(2300);
	
	
	/*************************************************************
	 *
	 * @decription: create shipping address
	 *
	 * @param: dto
	 * @return: none
	 *
	 * @date: 12/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 **************************************************************/
	@Override
	public Address createShippingAddress(AddressDTO addressExtDTO) {
		
		Address address = new Address();
		
		address.setIsShippingAddress(true);
		
		address.setFullAddress(addressExtDTO.getFullAddress());
		
		if (addressExtDTO.getDistrictId() != null ) {
			District district = districtService.findOneById(addressExtDTO.getDistrictId())
					.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.DISTRICT_NOT_FOUND));
			address.setDistrict(district);
		}
		if (addressExtDTO.getProvinceId() != null) {
			Province province = provinceService.findOneById(addressExtDTO.getProvinceId())
					.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.PROVINCE_NOT_FOUND));
			address.setProvince(province);
		}
		if (addressExtDTO.getWardsId() != null) {
			Wards ward = wardsService.findOneById(addressExtDTO.getWardsId())
					.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.WARDS_NOT_FOUND));
			address.setWards(ward);
		}
		if (addressExtDTO.getCountryId() != null) {
			Country country = countryService.findOneById(addressExtDTO.getCountryId())
					.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.COUNTRY_NOT_FOUND));
			address.setCountry(country);
		}
		
		return addressConsumerRepository.save(address);
	}
	
	/*************************************************************
	 *
	 * @decription: calculate kilometers address
	 *
	 * @param: lat, lng
	 * @return: map
	 *
	 * @date: 12/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 *************************************************************/
	@Override
	public Map<String, Object> calculateShippingAddress(Double lat, Double lng) {
		Map<String, Object> result = new HashMap<>();
		double distance = DistanceUtils.distanceBetween2Points(latitude_main, longitude_main, lat, lng);
		result.put("distance", distance);
		result.put("shippingFee", pricePerKilometer.multiply(BigDecimal.valueOf(distance)));
		return result;
	}
}
