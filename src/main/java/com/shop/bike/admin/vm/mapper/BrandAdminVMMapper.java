package com.shop.bike.admin.vm.mapper;

import com.shop.bike.admin.vm.BrandAdminVM;
import com.shop.bike.entity.Brand;
import com.shop.bike.service.EntityMapper;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface BrandAdminVMMapper extends EntityMapper<BrandAdminVM, Brand> {
}
