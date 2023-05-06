package com.shop.bike.vm.mapper;


import com.shop.bike.entity.District;
import com.shop.bike.service.EntityMapper;
import com.shop.bike.vm.DistrictVM;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link District} and its DTO {@link DistrictVM}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DistrictVMMapper extends EntityMapper<DistrictVM, District> {

}
