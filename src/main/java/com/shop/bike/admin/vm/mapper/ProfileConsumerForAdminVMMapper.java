package com.shop.bike.admin.vm.mapper;

import com.shop.bike.admin.vm.ProfileAdminVM;
import com.shop.bike.admin.vm.ProfileConsumerForAdminVM;
import com.shop.bike.entity.User;
import com.shop.bike.service.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link User} and its DTO {@link ProfileAdminVM}.
 */
@Mapper(componentModel = "spring")
public interface ProfileConsumerForAdminVMMapper extends EntityMapper<ProfileConsumerForAdminVM, User> {
}
