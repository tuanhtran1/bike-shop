package com.shop.bike.repository;

import com.shop.bike.entity.Address;
import com.shop.bike.entity.Brand;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface AddressRepository extends JpaRepository<Address, Long> {

}
