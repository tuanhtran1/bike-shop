package com.shop.bike.vm.mapper;

import com.shop.bike.entity.CouponDiscount;
import com.shop.bike.service.EntityMapper;
import com.shop.bike.vm.CouponDiscountVM;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link CouponDiscount} and its DTO {@link CouponDiscountVM}.
 */
@Mapper(componentModel = "spring")
public interface CouponDiscountVMMapper extends EntityMapper<CouponDiscountVM, CouponDiscount> {
}
