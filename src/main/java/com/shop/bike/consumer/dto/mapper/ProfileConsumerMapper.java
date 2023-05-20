package com.shop.bike.consumer.dto.mapper;

import com.shop.bike.admin.dto.CouponDiscountAdminDTO;
import com.shop.bike.consumer.dto.ProfileConsumerDTO;
import com.shop.bike.entity.CouponDiscount;
import com.shop.bike.entity.User;
import com.shop.bike.service.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link User} and its DTO {@link ProfileConsumerDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProfileConsumerMapper extends EntityMapper<ProfileConsumerDTO, User> {
}
