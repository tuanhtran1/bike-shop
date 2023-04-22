package com.shop.bike.admin.vm.mapper;

import com.shop.bike.admin.dto.ProductAdminDTO;
import com.shop.bike.admin.vm.ProductAdminVM;
import com.shop.bike.entity.Product;
import com.shop.bike.service.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductAdminVM}.
 */
@Mapper(componentModel = "spring")
public interface ProductAdminVMMapper extends EntityMapper<ProductAdminVM, Product> {
}
