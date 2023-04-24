package com.shop.bike.admin.repository;

import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(ApplicationConstant.ADMIN)
public interface MediaAdminRepository extends MediaRepository {

}
