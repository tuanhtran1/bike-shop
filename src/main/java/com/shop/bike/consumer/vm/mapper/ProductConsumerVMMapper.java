package com.shop.bike.consumer.vm.mapper;

import com.shop.bike.consumer.vm.ProductBaseConsumerVM;
import com.shop.bike.consumer.vm.ProductConsumerVM;
import com.shop.bike.entity.Product;
import com.shop.bike.service.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductConsumerVM}.
 */
@Mapper(componentModel = "spring")
public interface ProductConsumerVMMapper extends EntityMapper<ProductConsumerVM, Product> {
}
