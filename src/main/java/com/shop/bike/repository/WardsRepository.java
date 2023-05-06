package com.shop.bike.repository;

import com.shop.bike.entity.Wards;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface WardsRepository extends JpaRepository<Wards, Long> {
	
	Page<Wards> findAllByDistrictId(Long districtId, Pageable pageable);
}
