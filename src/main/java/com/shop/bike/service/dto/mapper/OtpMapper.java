package com.shop.bike.service.dto.mapper;

import com.shop.bike.entity.Media;
import com.shop.bike.entity.Otp;
import com.shop.bike.service.EntityMapper;
import com.shop.bike.service.dto.MediaDTO;
import com.shop.bike.service.dto.OtpDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Otp} and its DTO {@link OtpDTO}.
 */
@Mapper(componentModel = "spring")
public interface OtpMapper extends EntityMapper<OtpDTO, Otp> {
}
