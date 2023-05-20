package com.shop.bike.admin.vm.mapper;

import com.shop.bike.admin.vm.WareHouseVM;
import com.shop.bike.entity.WareHouse;
import com.shop.bike.service.EntityMapper;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface WareHouseVMMapper extends EntityMapper<WareHouseVM, WareHouse> {
}
