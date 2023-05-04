package com.shop.bike.consumer.vm.mapper;

import com.shop.bike.consumer.vm.ProfileConsumerVM;
import com.shop.bike.entity.User;
import com.shop.bike.service.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link User} and its DTO {@link ProfileConsumerVM}.
 */
@Mapper(componentModel = "spring")
public interface ProfileConsumerVMMapper extends EntityMapper<ProfileConsumerVM, User> {
}
