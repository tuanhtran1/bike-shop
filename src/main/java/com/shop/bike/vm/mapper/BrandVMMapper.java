package com.shop.bike.vm.mapper;


import com.shop.bike.entity.Brand;
import com.shop.bike.entity.Country;
import com.shop.bike.service.EntityMapper;
import com.shop.bike.vm.BrandVM;
import com.shop.bike.vm.CountryVM;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Brand} and its VM {@link BrandVM}.
 */
@Mapper(componentModel = "spring")
public interface BrandVMMapper extends EntityMapper<BrandVM, Brand> {
	
}
