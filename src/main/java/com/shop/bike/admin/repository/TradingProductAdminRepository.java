package com.shop.bike.admin.repository;

import com.shop.bike.admin.dto.TradingProductFilterDTO;
import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.entity.TradingProduct;
import com.shop.bike.entity.enumeration.ActionStatus;
import com.shop.bike.repository.TradingProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier(ApplicationConstant.ADMIN)
public interface TradingProductAdminRepository extends TradingProductRepository {
	
	@Query("select p from TradingProduct p where p.status <>'DELETED' "
			+ "and p.product.productStatus<>'DELETED' "
			+ "and ((:#{#filters.keyword} is null) or (lower(unaccent(p.name)) like concat('%',lower(unaccent(:#{#filters.keyword})),'%')) "
			+ "or (lower(unaccent(p.itemCode)) like concat('%',lower(unaccent(:#{#filters.keyword})),'%'))) "
			+ "and ((:#{#filters.itemCode} is null) or (lower(unaccent(p.itemCode)) like concat('%',lower(unaccent(:#{#filters.itemCode})),'%'))) "
			+ "and ((:#{#filters.priceFrom} is null) or (p.price >=:#{#filters.priceFrom})) "
			+ "and ((:#{#filters.priceTo} is null) or (p.price <=:#{#filters.priceTo})) "
			+ "and ((:#{#filters.status} is null) or (p.status =:#{#filters.status})) "
			+ "and ((:#{#filters.approveStatus} is null) or (p.approveStatus =:#{#filters.approveStatus})) "
			+ "and ((:#{#filters.name} is null) or (lower(unaccent(p.name)) like concat('%',lower(unaccent(:#{#filters.name})),'%'))) "
			+ "and ((:#{#filters.userId} is null) or (p.product.userId =:#{#filters.userId})) "
			+ "")
	Page<TradingProduct> findAll(@Param("filters") TradingProductFilterDTO filters, Pageable pageable);

	List<TradingProduct> findAllByProductId(Long productId);
	
	Optional<TradingProduct> findByIdAndStatusNot(Long id, ActionStatus status);
	
	
}
