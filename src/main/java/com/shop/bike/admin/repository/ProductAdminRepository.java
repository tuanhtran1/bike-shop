package com.shop.bike.admin.repository;

import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(ApplicationConstant.ADMIN)
public interface ProductAdminRepository extends ProductRepository {

}
