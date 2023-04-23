package com.shop.bike.consumer.repository;

import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.entity.MyOrder;
import com.shop.bike.repository.MyOrderRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(ApplicationConstant.CONSUMER)
public interface MyOrderConsumerRepository extends MyOrderRepository {

}
