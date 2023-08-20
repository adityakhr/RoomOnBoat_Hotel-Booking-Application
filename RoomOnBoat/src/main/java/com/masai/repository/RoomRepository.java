package com.masai.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.masai.model.Room;

public interface RoomRepository extends JpaRepository<Room, Integer>, PagingAndSortingRepository<Room, Integer> {
	@Query("SELECT r FROM Room r WHERE r.property.propertyId = ?1")
	Page<Room> findRoom(Integer propertyId, Pageable pageNumber);
	@Query("SELECT r FROM Room r WHERE r.property.propertyId = ?1")
	Page<Room> findRoomForHost(Integer propertyId, Pageable pageNumber);

}
