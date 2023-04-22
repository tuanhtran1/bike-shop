package com.shop.bike.repository;

import com.shop.bike.admin.dto.ProductFilterDTO;
import com.shop.bike.entity.Product;
import com.shop.bike.entity.enumeration.ActionStatus;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Primary
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Optional<Product> findByIdAndProductStatusNot(Long id, ActionStatus status);
	
	@Query("select p from Product p where p.productStatus<>'DELETED' "
			+ "and ((:#{#filters.keyword} is null) "
			+ "or (lower(unaccent(p.name)) like concat('%',lower(unaccent(:#{#filters.keyword})),'%')) "
			+ "or (lower(unaccent(p.code)) like concat('%',lower(unaccent(:#{#filters.keyword})),'%'))) "
			+ "and ((:#{#filters.code} is null) or (p.code like %:#{#filters.code}%)) "
			+ "and ((:#{#filters.oldCode} is null) or (p.oldCode like %:#{#filters.oldCode}%)) "
			+ "and ((:#{#filters.name} is null) or (lower(unaccent(p.name)) like concat('%',lower(unaccent(:#{#filters.name})),'%'))) "
			+ "and ((:#{#filters.brandId} is null) or (p.brand.id =:#{#filters.brandId})) "
			+ "and ((:#{#filters.brandName} is null) or (lower(unaccent(p.brand.name)) like concat('%',lower(unaccent(:#{#filters.brandName})),'%'))) "
			+ "and ((:#{#filters.productStatus} is null) or (p.productStatus =:#{#filters.productStatus})) "
			+ "and ((:#{#filters.origin} is null) or (lower(unaccent(p.origin)) like concat('%',lower(unaccent(:#{#filters.origin})),'%'))) "
			+ "and (:#{#filters.createdDateFrom} is null or p.createdDate>=:#{#filters.createdDateFrom}) "
			+ "and (:#{#filters.createdDateTo} is null or p.createdDate<=:#{#filters.createdDateTo}) "
			+ "and (:#{#filters.lastModifiedDateFrom} is null or p.lastModifiedDate>=:#{#filters.lastModifiedDateFrom}) "
			+ "and (:#{#filters.lastModifiedDateTo} is null or p.lastModifiedDate<=:#{#filters.lastModifiedDateTo}) "
	)
	Page<Product> findAll(@Param("filters") ProductFilterDTO filters, Pageable page);
}
