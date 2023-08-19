package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.ApplicationException;
import com.masai.model.Booking;
import com.masai.model.Property;
import com.masai.model.Room;
import com.masai.model.Users;
import com.masai.service.UserFunctionalityInterfaceImplementation;
import com.masai.update.UpdateEmail;
import com.masai.update.UpdateName;
import com.masai.update.UpdatePassword;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserFunctinality {
	@Autowired
	private UserFunctionalityInterfaceImplementation ufi;
	@GetMapping("/get_properties")
	public ResponseEntity<List<Property>> getAllProperties(@RequestParam(defaultValue = "0" ,required = false) Integer page, @RequestParam(defaultValue = "10" ,required = false) Integer count, @RequestParam(defaultValue = "asc",required = false) String order  ) throws ApplicationException{
		log.info("Getting Host");
		List<Property> properties=ufi.getAllProperties(page,count,order);
		return new ResponseEntity<>(properties,HttpStatus.ACCEPTED);
	}
	@GetMapping("property/{propertyId}/get_rooms")
	public ResponseEntity<List<Room>> getRooms(@PathVariable("{propertyId}")Integer propertyId,@RequestParam(defaultValue = "0" ,required = false) Integer page, @RequestParam(defaultValue = "10" ,required = false) Integer count, @RequestParam(defaultValue = "asc",required = false) String order  ) throws ApplicationException{
		log.info("Getting Host");
		List<Room> rooms=ufi.getRooms(propertyId,page,count,order);
		return new ResponseEntity<>(rooms,HttpStatus.ACCEPTED);
	}
	@GetMapping("/{userId}/get_your_booking")
	public ResponseEntity<List<Booking>> getBookings(@PathVariable("{userId}")Integer userId,@RequestParam(defaultValue = "0" ,required = false) Integer page, @RequestParam(defaultValue = "10" ,required = false) Integer count, @RequestParam(defaultValue = "asc",required = false) String order  ) throws ApplicationException{
		log.info("Getting Host");
		List<Booking> bookings=ufi.getBookings(userId,page,count,order);
		return new ResponseEntity<>(bookings,HttpStatus.ACCEPTED);
	}
	@PostMapping("/{userId}/update_email")
	public ResponseEntity<Users> updateEmail(@PathVariable("userId") Integer userId , @RequestBody @Valid UpdateEmail updatedEmail) throws ApplicationException{
		log.info("updating host from restController...");
		Users user =  ufi.updateEmail(userId, updatedEmail);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	@PostMapping("/{userId}/update_password")
	public ResponseEntity<Users> updatePassword(@PathVariable("userId") Integer userId , @RequestBody @Valid UpdatePassword updatedPassword) throws ApplicationException{
		log.info("updating host from restController...");
		Users user =  ufi.updatePassword(userId, updatedPassword);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	@PostMapping("/{userId}/update_name")
	public ResponseEntity<Users> updateName(@PathVariable("userId") Integer userId , @RequestBody @Valid UpdateName updatedName) throws ApplicationException{
		log.info("updating host from restController...");
		Users user =  ufi.updateName(userId, updatedName);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	@PostMapping("/{userId}/book_room/{roomId}")
	public ResponseEntity<Room> bookRoom(@PathVariable("userId") Integer userId,@PathVariable("roomId") Integer roomId) throws ApplicationException{
		log.info("updating host from restController...");
		Room room =  ufi.bookRoom(userId,roomId);
		return new ResponseEntity<>(room,HttpStatus.OK);
	}
	@PostMapping("/{userId}/booking/{bookingId}/delete_booked_room/{roomId}")
	public ResponseEntity<Room> deleteBookedRoom(@PathVariable("userId") Integer userId,@PathVariable("bookingId") Integer bookingId,@PathVariable("roomId") Integer roomId) throws ApplicationException{
		log.info("updating host from restController...");
		Room room =  ufi.deleteBookedRoom(userId,bookingId,roomId);
		return new ResponseEntity<>(room,HttpStatus.OK);
	}
	@PostMapping("/{userId}/confirm_booking/{bookingId}")
	public ResponseEntity<Booking> confirmBooking(@PathVariable("userId") Integer userId,@PathVariable("bookingId") Integer bookingId) throws ApplicationException{
		log.info("updating host from restController...");
		Booking booking =  ufi.confirmBooking(userId,bookingId);
		return new ResponseEntity<>(booking,HttpStatus.OK);
	}
	@DeleteMapping("/{userId}/delete_account")
	public ResponseEntity<Users> deleteYourAccount(@PathVariable("userId") Integer userId ) throws ApplicationException{
		log.info("deleting host from restController...");
		Users user =  ufi.deleteYourAccount(userId);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
}
