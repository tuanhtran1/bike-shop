package com.shop.bike.repository;

import com.shop.bike.entity.MyOrder;
import com.shop.bike.entity.MyOrderDetails;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface MyOrderDetailRepository extends JpaRepository<MyOrderDetails, Long> {

}
