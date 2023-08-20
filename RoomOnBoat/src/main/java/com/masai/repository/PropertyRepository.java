package com.masai.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.masai.model.Property;

public interface PropertyRepository extends JpaRepository<Property, Integer>, PagingAndSortingRepository<Property, Integer>{
	@Query("Select p From Property p Where p.host.hostId=?1")
	Page<Property> findPropertyForHost(Integer hostId, Pageable pageNumber);
}
