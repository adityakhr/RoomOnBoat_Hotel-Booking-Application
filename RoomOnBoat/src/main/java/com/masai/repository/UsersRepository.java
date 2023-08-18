package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.masai.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>, PagingAndSortingRepository<Users, Integer> {
	@Query("Select u From Users u Where u.email=?1")
	public Optional<Users> findByEmail(String email); 
}
