package com.shop.bike.repository;


import com.shop.bike.entity.Role;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Primary
public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByCode(String code);

}
