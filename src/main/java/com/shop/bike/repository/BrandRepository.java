package com.shop.bike.repository;

import com.shop.bike.entity.Brand;
import com.shop.bike.entity.TradingProduct;
import com.shop.bike.entity.enumeration.ActionStatus;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public interface BrandRepository extends JpaRepository<Brand, Long> {
	List<Brand> findAllByStatusNot(ActionStatus status);
}
