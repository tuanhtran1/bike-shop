package com.shop.bike.admin.dto.mapper;

import com.shop.bike.admin.dto.ProductAdminDTO;
import com.shop.bike.entity.Product;
import com.shop.bike.service.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductAdminDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductAdminMapper extends EntityMapper<ProductAdminDTO, Product> {
}
