package com.masai.service;

import java.util.List;

import com.masai.exception.ApplicationException;
import com.masai.model.Booking;
import com.masai.model.Host;
import com.masai.model.Property;
import com.masai.model.Room;
import com.masai.update.UpdateEmail;
import com.masai.update.UpdateName;
import com.masai.update.UpdatePassword;

public interface HostFunctinalityInterface {
	public Property addNewProperty(Property property, Integer hostId) throws ApplicationException;

	public Room addRoomToProperty(Room room, Integer propertyId, Integer hostId) throws ApplicationException;

	public Host deleteYourAccount(Integer hostId) throws ApplicationException;

	public Host updateEmail(Integer hostId, UpdateEmail updatedEmail) throws ApplicationException;

	public Host updateName(Integer hostId, UpdateName updatedName) throws ApplicationException;

	public Host updatePassword(Integer hostId, UpdatePassword updatedPassword) throws ApplicationException;

	public Room deleteRoom(Integer hostId, Integer propertyId, Integer roomId) throws ApplicationException;

	public Property deleteProperty(Integer hostId, Integer propertyId) throws ApplicationException;

	public Booking updateBookingStatus(Integer hostId, Integer bookingId) throws ApplicationException;

	List<Room> fetchAllRooms(Integer propertyId, Integer hostId, Integer page, Integer count, String order)
			throws ApplicationException;

	List<Property> fetchAllProperties(Integer hostId, Integer page, Integer count, String order)
			throws ApplicationException;

	
	
}
