package com.shop.bike.consumer.vm.mapper;

import com.shop.bike.consumer.vm.MyOrderConsumerVM;
import com.shop.bike.entity.MyOrder;
import com.shop.bike.service.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link MyOrder} and its DTO {@link MyOrderConsumerVM}.
 */
@Mapper(componentModel = "spring")
public interface MyOrderConsumerVMMapper extends EntityMapper<MyOrderConsumerVM, MyOrder> {
}
