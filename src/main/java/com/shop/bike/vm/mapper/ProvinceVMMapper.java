package com.shop.bike.vm.mapper;


import com.shop.bike.entity.Province;
import com.shop.bike.service.EntityMapper;
import com.shop.bike.vm.ProvinceVM;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Province} and its DTO {@link ProvinceVM}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProvinceVMMapper extends EntityMapper<ProvinceVM, Province> {

}
