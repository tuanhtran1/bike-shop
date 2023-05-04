package com.shop.bike.repository;

import com.shop.bike.entity.User;
import com.shop.bike.entity.enumeration.ActionStatus;
import com.shop.bike.entity.enumeration.AuthorityType;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u where u.status = 1")
	List<User> findAllUser();
	
	@Query(value = "SELECT u.* from `user` u left join user_role ur on u.id = ur.user_id where u.user_name =:userName", nativeQuery = true)
	Optional<User> findOneWithAuthoritiesByLogin(@Param("userName") String login);
	
	@Query(
			"SELECT DISTINCT p from User p join p.roles a where p.id=:id " +
					" and (:status is null or p.status=:status) " +
					"and (COALESCE(:authorities, null) is null or a.code=:authorities)"
	)
	Optional<User> findByIdAndAuthorityAndStatus(
			@Param("id") Long id,
			@Param("authorities") List<String> authorities,
			@Param("status") Integer status
	);
}
