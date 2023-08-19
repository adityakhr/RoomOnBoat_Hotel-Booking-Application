package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.ApplicationException;
import com.masai.model.Admin;
import com.masai.model.Host;
import com.masai.model.Property;
import com.masai.model.Room;
import com.masai.model.Users;
import com.masai.service.AdminFunctinalityInterfaceImplimentation;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminFuctionality {
	
	@Autowired
	private AdminFunctinalityInterfaceImplimentation afi;
	
	
	@GetMapping("/get_hosts")
	public ResponseEntity<List<Host>> getAllHost(@RequestParam(defaultValue = "0" ,required = false) Integer page, @RequestParam(defaultValue = "10" ,required = false) Integer count, @RequestParam(defaultValue = "asc",required = false) String order  ) throws ApplicationException{
		log.info("Getting Host");
		List<Host> hosts=afi.getAllHost(page,count,order);
		return new ResponseEntity<>(hosts,HttpStatus.ACCEPTED);
	}
	@GetMapping("/get_users")
	public ResponseEntity<List<Users>> getAllUser(@RequestParam(defaultValue = "0" ,required = false) Integer page, @RequestParam(defaultValue = "10" ,required = false) Integer count, @RequestParam(defaultValue = "asc",required = false) String order  ) throws ApplicationException{
		log.info("Getting User");
		List<Users> users=afi.getAllUser(page,count,order);
		return new ResponseEntity<>(users,HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/delete_host/{hostId}")
	public ResponseEntity<Host> DeleteHost(@PathVariable("hostId")Integer hostId) throws ApplicationException{
		log.info("Deleting Host");
		Host host=afi.deleteHost(hostId);
		return new ResponseEntity<>(host,HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/delete_user/{userId}")
	public ResponseEntity<Users> DeleteUser(@PathVariable("userId")Integer userId) throws ApplicationException{
		log.info("Deleting User");
		Users user=afi.deleteUser(userId);
		return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
	}
	@GetMapping("/get_admins")
	public ResponseEntity<List<Admin>> getAlladmin(@RequestParam(defaultValue = "0" ,required = false) Integer page, @RequestParam(defaultValue = "10" ,required = false) Integer count, @RequestParam(defaultValue = "asc",required = false) String order ) throws ApplicationException{
		log.info("Getting User");
		List<Admin> admins=afi.getAlladmin(page,count,order);
		return new ResponseEntity<>(admins,HttpStatus.ACCEPTED);
	}
	@GetMapping("/get_properties")
	public ResponseEntity<List<Property>> getAllProperties(@RequestParam(defaultValue = "0" ,required = false) Integer page, @RequestParam(defaultValue = "10" ,required = false) Integer count, @RequestParam(defaultValue = "asc",required = false) String order  ) throws ApplicationException{
		log.info("Getting Host");
		List<Property> properties=afi.getAllProperties(page,count,order);
		return new ResponseEntity<>(properties,HttpStatus.ACCEPTED);
	}
	@GetMapping("/get_rooms")
	public ResponseEntity<List<Room>> getAllRooms(@RequestParam(defaultValue = "0" ,required = false) Integer page, @RequestParam(defaultValue = "10" ,required = false) Integer count, @RequestParam(defaultValue = "asc",required = false) String order ) throws ApplicationException{
		log.info("Getting User");
		List<Room> rooms=afi.getAllRooms(page,count,order);
		return new ResponseEntity<>(rooms,HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/delete_room/{roomId}")
	public ResponseEntity<Room> DeleteRoom(@PathVariable("roomId")Integer roomId) throws ApplicationException{
		log.info("Deleting Host");
		Room room=afi.deleteRoom(roomId);
		return new ResponseEntity<>(room,HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/delete_property/{propertyId}")
	public ResponseEntity<Property> DeleteProperty(@PathVariable("propertyId")Integer propertyId) throws ApplicationException{
		log.info("Deleting User");
		Property property=afi.deleteProperty(propertyId);
		return new ResponseEntity<>(property,HttpStatus.ACCEPTED);
	}
	@PostMapping("/add_admin/host/{hostId}")
	public ResponseEntity<Admin> addHostAdmin(@PathVariable("hostId")Integer hostId) throws ApplicationException{
		log.info("Deleting User");
		Admin admin=afi.addHostAdmin(hostId);
		return new ResponseEntity<>(admin,HttpStatus.ACCEPTED);
	}
	@PostMapping("/add_admin/user/{userId}")
	public ResponseEntity<Admin> addUserAdmin(@PathVariable("userId")Integer userId) throws ApplicationException{
		log.info("Deleting User");
		Admin admin=afi.addUserAdmin(userId);
		return new ResponseEntity<>(admin,HttpStatus.ACCEPTED);
	}
	
}
