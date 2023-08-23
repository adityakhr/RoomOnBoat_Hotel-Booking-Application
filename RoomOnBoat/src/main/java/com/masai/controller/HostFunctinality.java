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
		log.info("Host is Adding property in Controller...");
		Property prop =  hfi.addNewProperty(property,hostId);
		return new ResponseEntity<>(prop,HttpStatus.ACCEPTED);
	}
	@PostMapping("/{hostId}/property/{propertyId}/rooms")
	public ResponseEntity<Room> addRoomToProperty(@RequestBody @Valid Room room, @PathVariable("propertyId") Integer propertyId ,@PathVariable("hostId") Integer hostId ) throws ApplicationException{
		log.info("Host is Adding Room to Property in Controller...");
		Room roomm =  hfi.addRoomToProperty(room,propertyId,hostId);
		return new ResponseEntity<>(roomm,HttpStatus.ACCEPTED);
	}
	@GetMapping("/{hostId}/property/{propertyId}/rooms")
	public ResponseEntity<List<Room>> fetchAllRooms(@PathVariable("propertyId") Integer propertyId,@PathVariable("hostId") Integer hostId,@RequestParam(defaultValue = "0" ,required = false) Integer page, @RequestParam(defaultValue = "10" ,required = false) Integer count, @RequestParam(defaultValue = "asc",required = false) String order ) throws ApplicationException{
		log.info("Host is Getting property's room in Controller...");
		List<Room> rooms =  hfi.fetchAllRooms(propertyId,hostId,page,count,order);
		return new ResponseEntity<>(rooms,HttpStatus.OK);
	}
	@GetMapping("/{hostId}/properties")
	public ResponseEntity<List<Property>> fetchAllProperties(@PathVariable("hostId") Integer hostId,@RequestParam(defaultValue = "0" ,required = false) Integer page, @RequestParam(defaultValue = "10" ,required = false) Integer count, @RequestParam(defaultValue = "asc",required = false) String order ) throws ApplicationException{
		log.info("Host is Getting property in Controller...");
		List<Property> properties =  hfi.fetchAllProperties(hostId,page,count,order);
		return new ResponseEntity<>(properties,HttpStatus.OK);
	}
	@PostMapping("/{hostId}/update_password")
	public ResponseEntity<Host> updatePassword(@PathVariable("hostId") Integer hostId , @RequestBody @Valid UpdatePassword updatedPassword) throws ApplicationException{
		log.info("Host is Updating Password in Controller...");
		Host host =  hfi.updatePassword(hostId, updatedPassword);
		return new ResponseEntity<>(host,HttpStatus.OK);
	}
	@PostMapping("/{hostId}/update_status/booking/{bookingId}")
	public ResponseEntity<Booking> updateBookingStatus(@PathVariable("hostId") Integer hostId , @PathVariable("bookingId") Integer bookingId ) throws ApplicationException{
		log.info("Host is Updating Status of Rooms in Controller...");
		Booking booking =  hfi.updateBookingStatus(hostId, bookingId);
		return new ResponseEntity<>(booking,HttpStatus.OK);
	}
	@PostMapping("/{hostId}/update_name")
	public ResponseEntity<Host> updateName(@PathVariable("hostId") Integer hostId , @RequestBody @Valid UpdateName updatedName) throws ApplicationException{
		log.info("Host is Updating Name in Controller...");
		Host host =  hfi.updateName(hostId, updatedName);
		return new ResponseEntity<>(host,HttpStatus.OK);
	}
	@DeleteMapping("/{hostId}/delete_account")
	public ResponseEntity<Host> deleteYourAccount(@PathVariable("hostId") Integer hostId ) throws ApplicationException{
		log.info("Host is Deleting his Account in Controller...");
		Host host =  hfi.deleteYourAccount(hostId);
		return new ResponseEntity<>(host,HttpStatus.OK);
	}
	
	@DeleteMapping("/{hostId}/property/{propertyId}/delete_room/{roomId}")
	public ResponseEntity<Room> deleteRoom(@PathVariable("hostId") Integer hostId,@PathVariable("propertyId") Integer propertyId,@PathVariable("roomId") Integer roomId ) throws ApplicationException{
		log.info("Host is Deleting his Property's Room in Controller...");
		Room room =  hfi.deleteRoom(hostId,propertyId,roomId);
		return new ResponseEntity<>(room,HttpStatus.OK);
	}
	@DeleteMapping("/{hostId}/delete_property/{propertyId}")
	public ResponseEntity<Property> deleteProperty(@PathVariable("hostId") Integer hostId, @PathVariable("propertyId") Integer propertyId) throws ApplicationException{
		log.info("Host is Deleting his Property in Controller...");
		Property property =  hfi.deleteProperty(hostId,propertyId);
		return new ResponseEntity<>(property,HttpStatus.OK);
	}
}
