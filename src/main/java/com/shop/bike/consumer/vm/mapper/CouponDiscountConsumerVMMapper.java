package com.shop.bike.consumer.vm.mapper;

import com.shop.bike.consumer.vm.CouponDiscountConsumerVM;
import com.shop.bike.entity.CouponDiscount;
import com.shop.bike.service.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link CouponDiscount} and its DTO {@link ProductConsumerVMMapper}.
 */
@Mapper(componentModel = "spring")
public interface CouponDiscountConsumerVMMapper extends EntityMapper<CouponDiscountConsumerVM, CouponDiscount> {
}
