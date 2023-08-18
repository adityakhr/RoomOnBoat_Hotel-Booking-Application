package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.masai.model.Property;

public interface PropertyRepository extends JpaRepository<Property, Integer>, PagingAndSortingRepository<Property, Integer>{
	

}