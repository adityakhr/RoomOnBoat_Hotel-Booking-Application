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
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.ApplicationException;
import com.masai.model.Booking;
import com.masai.model.Host;
import com.masai.model.Property;
import com.masai.model.Room;
import com.masai.service.HostFunctionlityInterfaceImplimentation;
import com.masai.update.UpdateEmail;
import com.masai.update.UpdateName;
import com.masai.update.UpdatePassword;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/host")
public class HostFunctinality {
	@Autowired
	private HostFunctionlityInterfaceImplimentation hfi;
	
	@PostMapping("/{hostId}/properties")
	public ResponseEntity<Property> addNewProperty(@RequestBody @Valid Property property, @PathVariable("hostId") Integer hostId ) throws ApplicationException{
		log.info("Adding property from restController...");
		Property prop =  hfi.addNewProperty(property,hostId);
		return new ResponseEntity<>(prop,HttpStatus.ACCEPTED);
	}
	@PostMapping("/{hostId}/property/{propertyId}/rooms")
	public ResponseEntity<Room> addRoomToProperty(@RequestBody @Valid Room room, @PathVariable("propertyId") Integer propertyId ,@PathVariable("hostId") Integer hostId ) throws ApplicationException{
		log.info("Adding room from restController...");
		Room roomm =  hfi.addRoomToProperty(room,propertyId,hostId);
		return new ResponseEntity<>(roomm,HttpStatus.ACCEPTED);
	}
	@GetMapping("/{hostId}/property/{propertyId}/rooms")
	public ResponseEntity<List<Room>> fetchAllRooms(@PathVariable("propertyId") Integer propertyId,@PathVariable("hostId") Integer hostId ) throws ApplicationException{
		log.info("Fetching room of property from restController...");
		List<Room> rooms =  hfi.fetchAllRooms(propertyId,hostId);
		return new ResponseEntity<>(rooms,HttpStatus.OK);
	}
	@GetMapping("/{hostId}/properties")
	public ResponseEntity<List<Property>> fetchAllProperties(@PathVariable("hostId") Integer hostId ) throws ApplicationException{
		log.info("fetching Property from restController...");
		List<Property> properties =  hfi.fetchAllProperties(hostId);
		return new ResponseEntity<>(properties,HttpStatus.OK);
	}
	@PostMapping("/{hostId}/update_email")
	public ResponseEntity<Host> updateEmail(@PathVariable("hostId") Integer hostId , @RequestBody @Valid UpdateEmail updatedEmail) throws ApplicationException{
		log.info("updating host from restController...");
		Host host =  hfi.updateEmail(hostId, updatedEmail);
		return new ResponseEntity<>(host,HttpStatus.OK);
	}
	@PostMapping("/{hostId}/update_password")
	public ResponseEntity<Host> updatePassword(@PathVariable("hostId") Integer hostId , @RequestBody @Valid UpdatePassword updatedPassword) throws ApplicationException{
		log.info("updating host from restController...");
		Host host =  hfi.updatePassword(hostId, updatedPassword);
		return new ResponseEntity<>(host,HttpStatus.OK);
	}
	@PostMapping("/{hostId}/update_status/booking/{bookingId}")
	public ResponseEntity<Booking> updateBookingStatus(@PathVariable("hostId") Integer hostId , @PathVariable("bookingId") Integer bookingId ) throws ApplicationException{
		log.info("updating host from restController...");
		Booking booking =  hfi.updateBookingStatus(hostId, bookingId);
		return new ResponseEntity<>(booking,HttpStatus.OK);
	}
	@PostMapping("/{hostId}/update_name")
	public ResponseEntity<Host> updateName(@PathVariable("hostId") Integer hostId , @RequestBody @Valid UpdateName updatedName) throws ApplicationException{
		log.info("updating host from restController...");
		Host host =  hfi.updateName(hostId, updatedName);
		return new ResponseEntity<>(host,HttpStatus.OK);
	}
	@DeleteMapping("/{hostId}/delete_account")
	public ResponseEntity<Host> deleteYourAccount(@PathVariable("hostId") Integer hostId ) throws ApplicationException{
		log.info("deleting host from restController...");
		Host host =  hfi.deleteYourAccount(hostId);
		return new ResponseEntity<>(host,HttpStatus.OK);
	}
	
	@DeleteMapping("/{hostId}/property/{propertyId}/delete_room/{roomId}")
	public ResponseEntity<Room> deleteRoom(@PathVariable("hostId") Integer hostId,@PathVariable("propertyId") Integer propertyId,@PathVariable("roomId") Integer roomId ) throws ApplicationException{
		log.info("deleting from restController...");
		Room room =  hfi.deleteRoom(hostId,propertyId,roomId);
		return new ResponseEntity<>(room,HttpStatus.OK);
	}
	@DeleteMapping("/{hostId}/delete_property/{propertyId}")
	public ResponseEntity<Property> deleteProperty(@PathVariable("hostId") Integer hostId, @PathVariable("propertyId") Integer propertyId) throws ApplicationException{
		log.info("deleting from restController...");
		Property property =  hfi.deleteProperty(hostId,propertyId);
		return new ResponseEntity<>(property,HttpStatus.OK);
	}
}
