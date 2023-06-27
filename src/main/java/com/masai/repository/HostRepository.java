package com.masai.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.masai.model.Host;


public interface HostRepository extends  JpaRepository<Host, Integer>, PagingAndSortingRepository<Host, Integer>{
}
