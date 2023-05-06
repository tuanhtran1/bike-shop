package com.shop.bike.admin.dto.mapper;

import com.shop.bike.admin.dto.BrandAdminDTO;
import com.shop.bike.entity.Brand;
import com.shop.bike.service.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Brand} and its DTO {@link BrandAdminDTO}.
 */
@Mapper(componentModel = "spring")
public interface BrandAdminMapper extends EntityMapper<BrandAdminDTO, Brand> {
}
