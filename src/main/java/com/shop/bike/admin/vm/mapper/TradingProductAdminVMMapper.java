package com.shop.bike.admin.vm.mapper;

import com.shop.bike.admin.vm.TradingProductAdminVM;
import com.shop.bike.entity.TradingProduct;
import com.shop.bike.service.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link TradingProduct} and its DTO {@link TradingProductAdminVM}.
 */
@Mapper(componentModel = "spring")
public interface TradingProductAdminVMMapper extends EntityMapper<TradingProductAdminVM, TradingProduct> {
	
	@Mapping(source = "product.id", target = "productId")
	@Mapping(source = "product.description", target = "description")
	@Mapping(source = "product.images", target = "images")
	TradingProductAdminVM toDto(TradingProduct tradingProduct);
	
}
