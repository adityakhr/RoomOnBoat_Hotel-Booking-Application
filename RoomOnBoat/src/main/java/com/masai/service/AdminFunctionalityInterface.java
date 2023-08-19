package com.masai.service;

import java.util.List;

import com.masai.exception.ApplicationException;
import com.masai.model.Admin;
import com.masai.model.Host;
import com.masai.model.Property;
import com.masai.model.Room;
import com.masai.model.Users;

public interface AdminFunctionalityInterface {

	Users deleteUser(Integer userId) throws ApplicationException;

	List<Users> getAllUser(int page, int count, String order) throws ApplicationException;

	List<Host> getAllHost(int page, int count, String order) throws ApplicationException;

	Host deleteHost(Integer hostId) throws ApplicationException;
	
	Property deleteProperty(Integer propertyId) throws ApplicationException;

	Room deleteRoom(Integer roomId) throws ApplicationException;

	List<Property> getAllProperties(int page, int count, String order) throws ApplicationException;

	List<Room> getAllRooms(int page, int count, String order) throws ApplicationException;

	Admin addUserAdmin(Integer id) throws ApplicationException;

	Admin addHostAdmin(Integer hostId) throws ApplicationException;

	List<Admin> getAlladmin(Integer page, Integer count, String order) throws ApplicationException;
}
