package com.shop.bike.repository;

import com.shop.bike.entity.WareHouse;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public interface WareHouseRepository extends JpaRepository<WareHouse, Long> {

	List<WareHouse> findAllByTradingProductId(Long id);
}
