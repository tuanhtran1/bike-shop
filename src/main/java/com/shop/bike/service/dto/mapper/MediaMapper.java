package com.shop.bike.service.dto.mapper;

import com.shop.bike.entity.Media;
import com.shop.bike.service.EntityMapper;
import com.shop.bike.service.dto.MediaDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Media} and its DTO {@link MediaDTO}.
 */
@Mapper(componentModel = "spring")
public interface MediaMapper extends EntityMapper<MediaDTO, Media> {
}
