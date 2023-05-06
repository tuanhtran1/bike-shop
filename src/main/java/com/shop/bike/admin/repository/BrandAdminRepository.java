package com.shop.bike.admin.repository;

import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.entity.Brand;
import com.shop.bike.entity.enumeration.ActionStatus;
import com.shop.bike.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier(ApplicationConstant.ADMIN)
public interface BrandAdminRepository extends BrandRepository {

	Optional<Brand> findByIdAndStatusNot(Long id, ActionStatus status);
	
	Optional<Brand> findOneByNameAndStatusNot(String name, ActionStatus status);
	
	List<Brand> findAllByStatusNot(ActionStatus status);
}
