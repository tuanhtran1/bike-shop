package com.shop.bike.consumer.repository;

import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(ApplicationConstant.CONSUMER)
public interface AddressConsumerRepository extends AddressRepository {

}
