package com.shop.bike.vm.mapper;

import com.shop.bike.entity.Wards;
import com.shop.bike.service.EntityMapper;
import com.shop.bike.vm.WardsVM;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Wards} and its DTO {@link WardsVM}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WardsVMMapper extends EntityMapper<WardsVM, Wards> {

}
