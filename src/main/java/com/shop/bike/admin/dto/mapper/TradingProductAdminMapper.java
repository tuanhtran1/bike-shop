package com.shop.bike.admin.dto.mapper;

import com.shop.bike.admin.dto.ProductAdminDTO;
import com.shop.bike.admin.dto.TradingProductAdminDTO;
import com.shop.bike.entity.Product;
import com.shop.bike.entity.TradingProduct;
import com.shop.bike.service.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link TradingProduct} and its DTO {@link TradingProductAdminDTO}.
 */
@Mapper(componentModel = "spring")
public interface TradingProductAdminMapper extends EntityMapper<TradingProductAdminDTO, TradingProduct> {
}
