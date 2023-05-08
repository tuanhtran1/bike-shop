package com.shop.bike.consumer.vm.mapper;

import com.shop.bike.consumer.vm.ProductBaseConsumerVM;
import com.shop.bike.entity.Product;
import com.shop.bike.service.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductBaseConsumerVM}.
 */
@Mapper(componentModel = "spring")
public interface ProductBaseConsumerVMMapper extends EntityMapper<ProductBaseConsumerVM, Product> {
}
