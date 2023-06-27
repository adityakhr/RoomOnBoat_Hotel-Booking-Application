package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.ApplicationException;
import com.masai.model.Booking;
import com.masai.model.Host;
import com.masai.model.Property;
import com.masai.model.Room;
import com.masai.model.User;
import com.masai.service.ApplicationServiceInterfaceImplimentation;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ApplicationController {
	@Autowired
	private ApplicationServiceInterfaceImplimentation asi;
	
	
	
	
	
//	:::::1:::::
	
	
	
	@PostMapping("/users")
	public ResponseEntity<String> addNewUser(@RequestBody @Valid User user) throws ApplicationException{
		log.info("Adding User");
		asi.addUser(user);
		return new ResponseEntity<String>("Added Susscessfully",HttpStatus.ACCEPTED);
	}
	@PostMapping("/bookings/{userId}")
	public ResponseEntity<String> addNewBooking(@PathVariable("userId") Integer userId,@RequestBody @Valid Booking booking) throws ApplicationException{
		log.info("Adding Booking");
		asi.addBooking(booking,userId);
		return new ResponseEntity<String>("Added Susscessfully",HttpStatus.ACCEPTED);
	}
	
	
	
	
	
//	:::ADMIN:::
	@PostMapping("/hosts")
	public ResponseEntity<String> addNewHost(@RequestBody @Valid Host host) throws ApplicationException{
		log.info("Adding Host");
		asi.addHost(host);
		return new ResponseEntity<String>("Added Susscessfully",HttpStatus.ACCEPTED);
	}
	@PostMapping("/properties/{hostId}")
	public ResponseEntity<String> addNewProperty(@PathVariable("hostId") Integer hostId,@RequestBody @Valid Property property) throws ApplicationException{
		log.info("Adding Property");
		asi.addProperty(property,hostId);
		return new ResponseEntity<String>("Added Susscessfully",HttpStatus.ACCEPTED);
	}
	@PostMapping("/rooms/{propertyId}")
	public ResponseEntity<String> addNewRoom(@PathVariable("propertyId") Integer proId,@RequestBody @Valid Room room) throws ApplicationException{
		log.info("Adding Room");
		asi.addRoom(room,proId);
		return new ResponseEntity<String>("Added Susscessfully",HttpStatus.ACCEPTED);
	}
	@PostMapping("/users/admin")
	public ResponseEntity<String> addNewUserAdmin(@RequestBody @Valid User user) throws ApplicationException{
		log.info("Adding User");
		asi.addUser(user);
		return new ResponseEntity<String>("Added Susscessfully",HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/bookings/{bookingId}/admin")
	public ResponseEntity<String> addNewUserAdmin(@PathVariable("bookingId")Integer bookingId) throws ApplicationException{
		log.info("Adding User");
		asi.deletebooking(bookingId);
		return new ResponseEntity<String>("Deleted Susscessfully",HttpStatus.OK);
	}
	@GetMapping("/users/admin")
	public ResponseEntity<List<User>> GetAllUser() throws ApplicationException{
		log.info("Adding User");
		List<User>users=asi.getAllUser();
		return new ResponseEntity<List<User>>(users,HttpStatus.ACCEPTED);
	}
	@GetMapping("/properties/admin")
	public ResponseEntity<List<Property>> GetAllProperties() throws ApplicationException{
		log.info("Adding User");
		List<Property>properties=asi.GetAllProperties();
		return new ResponseEntity<List<Property>>(properties,HttpStatus.ACCEPTED);
	}
	@GetMapping("/rooms/admin")
	public ResponseEntity<List<Room>> GetAllRooms() throws ApplicationException{
		log.info("Adding User");
		List<Room>rooms=asi.GetAllRooms();
		return new ResponseEntity<List<Room>>(rooms,HttpStatus.ACCEPTED);
	}
	
	
	
	
	
	
	
	
	@GetMapping("/signIn")
	public ResponseEntity<String> logInUserDetails(Authentication auth) throws ApplicationException {
		
		User user =asi.logInUserDetails(auth);
		
		return new ResponseEntity<String>(user.getName()+" Logged Susscessfully...",HttpStatus.ACCEPTED);
		
	}
	
}
