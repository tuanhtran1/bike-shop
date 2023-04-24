package com.shop.bike.repository;

import com.shop.bike.entity.Media;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Primary
public interface MediaRepository extends JpaRepository<Media, Long> {
	
	Optional<Media> findByPath(String path);
}
