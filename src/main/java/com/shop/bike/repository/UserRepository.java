package com.shop.bike.repository;

import com.shop.bike.admin.dto.ProfileFilterDTO;
import com.shop.bike.entity.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	@Query("select p from User p where p.status is not null "
			+ "and ((:#{#filters.keyword} is null) "
			+ "or (lower(unaccent(p.name)) like concat('%',lower(unaccent(:#{#filters.keyword})),'%')) "
			+ "or (lower(unaccent(p.email)) like concat('%',lower(unaccent(:#{#filters.keyword})),'%'))) "
			+ "and ((:#{#filters.phone} is null) or (p.phone like %:#{#filters.phone}%)) "
			+ "and ((:#{#filters.name} is null) or (lower(unaccent(p.name)) like concat('%',lower(unaccent(:#{#filters.name})),'%'))) "
			+ "and ((:#{#filters.id} is null) or (p.id =:#{#filters.id})) "
			+ "and ((:#{#filters.email} is null) or (lower(unaccent(p.email)) like concat('%',lower(unaccent(:#{#filters.email})),'%'))) "
			+ "and ((:#{#filters.status} is null) or (p.status =:#{#filters.status})) "
			+ "and (:#{#filters.createdDateFrom} is null or p.createdDate>=:#{#filters.createdDateFrom}) "
			+ "and (:#{#filters.createdDateTo} is null or p.createdDate<=:#{#filters.createdDateTo}) "
	)
	Page<User> findAll(@Param("filters") ProfileFilterDTO filters, Pageable page);
}
