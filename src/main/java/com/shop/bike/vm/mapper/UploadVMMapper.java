package com.shop.bike.vm.mapper;

import com.shop.bike.entity.Media;
import com.shop.bike.service.EntityMapper;
import com.shop.bike.vm.MediaVM;
import com.shop.bike.vm.UploadVM;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Media} and its DTO {@link MediaVM}.
 */
@Mapper(componentModel = "spring")
public interface UploadVMMapper extends EntityMapper<UploadVM, Media> {
}
