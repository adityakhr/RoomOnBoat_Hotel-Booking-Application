package com.masai.service;

import java.util.List;

import com.masai.exception.ApplicationException;
import com.masai.model.Booking;
import com.masai.model.Property;
import com.masai.model.Room;
import com.masai.model.Users;
import com.masai.update.BookingDetails;
import com.masai.update.UpdateEmail;
import com.masai.update.UpdateName;
import com.masai.update.UpdatePassword;

public interface UserFunctionalityInterface {

	List<Property> getAllProperties(int page, int count, String order) throws ApplicationException;

	List<Room> getRooms(Integer propertyId, Integer page, Integer count, String order) throws ApplicationException;

	Users updateName(Integer userId, UpdateName updatedName) throws ApplicationException;

	Users updatePassword(Integer userId, UpdatePassword updatedPassword) throws ApplicationException;

	Users updateEmail(Integer userId, UpdateEmail updatedEmail) throws ApplicationException;

	Users deleteYourAccount(Integer userId) throws ApplicationException;

	Room deleteBookedRoom(Integer userId, Integer bookingId, Integer roomId) throws ApplicationException;

	Booking confirmBooking(Integer userId, Integer bookingId) throws ApplicationException;

	Room bookRoom(Integer userId, Integer roomId, BookingDetails bd) throws ApplicationException;

}
