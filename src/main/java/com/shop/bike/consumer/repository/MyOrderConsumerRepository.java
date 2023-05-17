package com.shop.bike.consumer.repository;

import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.entity.MyOrder;
import com.shop.bike.repository.MyOrderRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier(ApplicationConstant.CONSUMER)
public interface MyOrderConsumerRepository extends MyOrderRepository {

	List<MyOrder> findAllByBuyerId(Long buyerId);
}
