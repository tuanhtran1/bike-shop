package com.shop.bike.vm.mapper;


import com.shop.bike.admin.vm.TradingProductAdminVM;
import com.shop.bike.entity.Brand;
import com.shop.bike.entity.TradingProduct;
import com.shop.bike.service.EntityMapper;
import com.shop.bike.vm.BrandVM;
import com.shop.bike.vm.TradingProductBaseVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link TradingProduct} and its VM {@link TradingProductBaseVM}.
 */
@Mapper(componentModel = "spring")
public interface TradingProductBaseVMMapper extends EntityMapper<TradingProductBaseVM, TradingProduct> {
	
	
	@Mapping(source = "product.id", target = "productId")
	@Mapping(source = "product.description", target = "description")
	@Mapping(source = "product.images", target = "images")
	TradingProductBaseVM toDto(TradingProduct tradingProduct);
}
