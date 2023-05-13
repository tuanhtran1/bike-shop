package com.shop.bike.consumer.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class AddressDTO implements Serializable {

    private Long id;

    @Size(max = 255)
    private String address;
	
//    private Double lat;
//
//    private Double lng;
	
    @Size(max = 255)
    private String fullAddress;

//    private String zipCode;
	
    private Long countryId;

    private Long provinceId;

    private Long districtId;

    private Long wardsId;
	
}
