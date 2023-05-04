package com.shop.bike.admin.vm.mapper;

import com.shop.bike.admin.vm.MyOrderAdminVM;
import com.shop.bike.entity.MyOrder;
import com.shop.bike.service.EntityMapper;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface MyOrderAdminVMMapper extends EntityMapper<MyOrderAdminVM, MyOrder> {
}
