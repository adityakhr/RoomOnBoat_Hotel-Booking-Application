package com.masai.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.masai.exception.ApplicationException;
import com.masai.model.Host;
import com.masai.model.Property;
import com.masai.model.Room;
import com.masai.model.User;

public interface ApplicationServiceInterface {

	void addHost(Host host) throws ApplicationException;

	void addRoom(Room room, int propertyId) throws ApplicationException;

	void addProperty(Property property, Integer hostId) throws ApplicationException;

	void addUser(User user) throws ApplicationException;

	User logInUserDetails(Authentication auth) throws ApplicationException;

	void deletebooking(Integer bookingId) throws ApplicationException;

	List<User> getAllUser() throws ApplicationException ;

	List<Property> GetAllProperties() throws ApplicationException ;

	List<Room> GetAllRooms() throws ApplicationException;

}
