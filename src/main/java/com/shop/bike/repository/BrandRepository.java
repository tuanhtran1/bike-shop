package com.shop.bike.repository;

import com.shop.bike.entity.Brand;
import com.shop.bike.entity.TradingProduct;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface BrandRepository extends JpaRepository<Brand, Long> {

}
