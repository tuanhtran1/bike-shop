package com.shop.bike.admin.vm.mapper;

import com.shop.bike.admin.vm.ProductAdminVM;
import com.shop.bike.admin.vm.TradingProductAdminVM;
import com.shop.bike.entity.Product;
import com.shop.bike.entity.TradingProduct;
import com.shop.bike.service.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link TradingProduct} and its DTO {@link TradingProductAdminVM}.
 */
@Mapper(componentModel = "spring")
public interface TradingProductAdminVMMapper extends EntityMapper<TradingProductAdminVM, TradingProduct> {
}
