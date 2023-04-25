package com.shop.bike.admin.dto.mapper;

import com.shop.bike.admin.dto.CouponDiscountAdminDTO;
import com.shop.bike.admin.dto.ProductAdminDTO;
import com.shop.bike.entity.CouponDiscount;
import com.shop.bike.entity.Product;
import com.shop.bike.service.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link CouponDiscount} and its DTO {@link CouponDiscountAdminDTO}.
 */
@Mapper(componentModel = "spring")
public interface CouponDiscountAdminMapper extends EntityMapper<CouponDiscountAdminDTO, CouponDiscount> {
}
