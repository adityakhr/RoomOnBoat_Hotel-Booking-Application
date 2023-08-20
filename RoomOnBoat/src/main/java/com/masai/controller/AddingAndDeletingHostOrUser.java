package com.masai.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.ApplicationException;
import com.masai.model.Host;
import com.masai.model.Users;
import com.masai.service.AddingHostOrUserServiceInterfaceImplimentation;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AddingAndDeletingHostOrUser {
	@Autowired
	private AddingHostOrUserServiceInterfaceImplimentation asi;
	
	@PostMapping("/add/hosts")
	public ResponseEntity<Host> addNewHost(@RequestBody @Valid Host host) throws ApplicationException{
		log.info("Adding new Host in controllerPackage...");
		Host hostt=asi.addHost(host);
		return new ResponseEntity<>(hostt,HttpStatus.ACCEPTED);
	}
	@PostMapping("/add/users")
	public ResponseEntity<Users> addNewUser(@RequestBody @Valid Users user) throws ApplicationException{
		log.info("Adding new User in controllerPackage...");
		Users userr=asi.addUser(user);
		return new ResponseEntity<>(userr,HttpStatus.ACCEPTED);
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
