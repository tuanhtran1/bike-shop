package com.shop.bike.consumer.vm;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.bike.vm.CountryVM;
import com.shop.bike.vm.DistrictVM;
import com.shop.bike.vm.ProvinceVM;
import com.shop.bike.vm.WardsVM;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class ShippingAddressVM implements Serializable {

    private Long id;

    @Size(max = 255)
    private String address;
	
    @Size(max = 255)
    private String fullAddress;
	
	@JsonIgnore
	private ProvinceVM province;
	
	@JsonIgnore
	private DistrictVM district;
	
	@JsonIgnore
	private WardsVM wards;
	@JsonIgnore
	private CountryVM country;
	
	private String countryName;
	
	private String provinceName;
	
	private String districtName;

	private String wardsName;
	
	public Long getCountryId() {
		return country != null ? country.getId() : null;
	}
	
	public String getCountryName() {
		if (country != null) {
			return country.getName();
		}
		return countryName;
	}
	
	public String getProvinceName() {
		if (province != null) {
			provinceName = province.getName();
		}
		return provinceName;
	}
	
	public Long getProvinceId() {
		return province != null ? province.getId() : null;
	}
	
	public String getDistrictName() {
		if (district != null) {
			districtName = district.getName();
		}
		return districtName;
	}
	
	public Long getDistrictId() {
		return district != null ? district.getId() : null;
	}
	
	public String getWardsName() {
		if (wards != null) {
			wardsName = wards.getName();
		}
		return wardsName;
	}
	
	public Long getWardsId() {
		return wards != null ? wards.getId() : null;
	}
}
