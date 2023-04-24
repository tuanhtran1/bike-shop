package com.shop.bike.vm.mapper;

import com.shop.bike.entity.Media;
import com.shop.bike.service.EntityMapper;
import com.shop.bike.vm.MediaVM;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Media} and its DTO {@link MediaVM}.
 */
@Mapper(componentModel = "spring")
public interface MediaVMMapper extends EntityMapper<MediaVM, Media> {
}
