package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.masai.model.Booking;

public interface BookingRepository extends  JpaRepository<Booking, Integer>, PagingAndSortingRepository<Booking, Integer> {
	
}
