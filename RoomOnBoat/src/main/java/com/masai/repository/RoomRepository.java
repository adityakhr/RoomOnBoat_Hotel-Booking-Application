package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.masai.model.Room;

public interface RoomRepository extends JpaRepository<Room, Integer>, PagingAndSortingRepository<Room, Integer> {

}
