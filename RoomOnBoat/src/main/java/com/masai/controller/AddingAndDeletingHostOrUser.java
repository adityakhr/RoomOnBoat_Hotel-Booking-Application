package com.masai.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.ApplicationException;
import com.masai.model.Host;
import com.masai.model.Users;
import com.masai.service.AddingAndDeletingHostOrUserServiceInterfaceImplimentation;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
public class AddingAndDeletingHostOrUser {
	@Autowired
	private AddingAndDeletingHostOrUserServiceInterfaceImplimentation asi;
	
	
//	:::::::NEW USER AND HOST:::::::
	@PostMapping("/add/hosts")
	public ResponseEntity<Host> addNewHost(@RequestBody @Valid Host host) throws ApplicationException{
		log.info("Adding Host");
		Host hostt=asi.addHost(host);
		return new ResponseEntity<>(hostt,HttpStatus.ACCEPTED);
	}
	@PostMapping("/add/users")
	public ResponseEntity<Users> addNewUser(@RequestBody @Valid Users user) throws ApplicationException{
		log.info("Adding User");
		Users userr=asi.addUser(user);
		return new ResponseEntity<>(userr,HttpStatus.ACCEPTED);
	}
	@GetMapping("/hosts")
	public ResponseEntity<List<Host>> getAllHost() throws ApplicationException{
		log.info("Getting Host");
		List<Host> hosts=asi.getAllHost();
		return new ResponseEntity<>(hosts,HttpStatus.ACCEPTED);
	}
	@GetMapping("/users")
	public ResponseEntity<List<Users>> getAllUser() throws ApplicationException{
		log.info("Getting User");
		List<Users> users=asi.getAllUser();
		return new ResponseEntity<>(users,HttpStatus.ACCEPTED);
	}
	
	
	
	
	
	
//	:::ADMIN:::
	
	
	
	@PostMapping("/users/admin")
	public ResponseEntity<String> addNewUserAdmin(@RequestBody @Valid Users user) throws ApplicationException{
		log.info("Adding User");
		asi.addUser(user);
		return new ResponseEntity<String>("Added Susscessfully",HttpStatus.ACCEPTED);
	}
	
	
	
	
//	::::::LOGIN END-POINTS::::::
//	@PostMapping("/logIn")
//	public ResponseEntity<Users> logInUserDetails(Authentication auth) throws ApplicationException {
//		
//		Users user =asi.logInUserDetails(auth);
//		
//		return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
//		
//	}
//	@PostMapping("/host/logIn")
//	public ResponseEntity<Host> logInHostDetails(Authentication auth) throws ApplicationException {
//		
//		Host host =asi.logInHostDetails(auth);
//		
//		return new ResponseEntity<>(host,HttpStatus.ACCEPTED);
//		
//	}
	
	
	
}
