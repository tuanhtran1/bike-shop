package com.shop.bike.repository;

import com.shop.bike.entity.Country;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public interface CountryRepository extends JpaRepository<Country, Long> {
	
	@Query("from Country c where (:keyword is null or c.name like %:keyword%) and c.status='ACTIVATED' order by c.name asc")
	List<Country> findAll(@Param("keyword") String keyword);
}
