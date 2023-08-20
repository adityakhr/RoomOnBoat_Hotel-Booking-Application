package com.masai.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.masai.model.Booking;

public interface BookingRepository extends  JpaRepository<Booking, Integer>, PagingAndSortingRepository<Booking, Integer> {
	@Query("Select b From Booking b Where b.user.userId=?1 And b.active=true")
	Booking findbooking(Integer userId);
	@Query("Select b From Booking b Where b.user.userId=?1 And b.active=false")
	Page<Booking> findBooking(Integer userId, Pageable pageNumber);
}
