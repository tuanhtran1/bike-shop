package com.shop.bike.consumer.repository;

import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.entity.Product;
import com.shop.bike.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(ApplicationConstant.CONSUMER)
public interface ProductConsumerRepository extends ProductRepository {
	
	@Query(value = "from Product p where p.productStatus = 'ACTIVATED'"
			+ " AND exists(select tp from p.tradingProducts tp where tp.status = 'ACTIVATED' and tp.approveStatus = 'APPROVED' and tp.stockQuantity > 0) "
			+ " AND (:keyword is null OR (lower(unaccent(p.name)) like concat('%',lower(unaccent(:keyword)),'%')))"
			+ " AND (:brandId is null OR p.brand.id =:brandId ) "
			+ "")
	Page<Product> findAllRunningProduct(@Param("keyword") String keyword,
						  @Param("brandId") Long brandId,
						  Pageable pageable);
}
