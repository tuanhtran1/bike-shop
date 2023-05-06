package com.shop.bike.repository;

import com.shop.bike.entity.Province;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface ProvinceRepository extends JpaRepository<Province, Long> {
	
	@Query("from Province p where (:countryId is null or p.countryId=:countryId) order by p.name")
	Page<Province> findAllByCountryIdOrStateId(@Param("countryId")Long countryId, Pageable pageable);
}
