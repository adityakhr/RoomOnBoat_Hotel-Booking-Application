package com.masai.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.masai.model.Booking;

public interface BookingRepository extends  JpaRepository<Booking, Integer>, PagingAndSortingRepository<Booking, Integer> {
	@Query("Select B From Booking B Where B.user.userId=?1 And B.active=true")
	Booking findbooking(Integer userId);
	@Query("Select B From Booking B Where B.user.userId=?1 And B.active=false")
	Page<Booking> findBooking(Integer userId, Pageable pageNumber);
}
