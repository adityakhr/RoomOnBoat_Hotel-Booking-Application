package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.ApplicationException;
import com.masai.model.Admin;
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
//	@PostMapping("/login/user")
//	public ResponseEntity<String> logInUserDetails(@RequestBody @Valid LoginClass loginDetails) throws ApplicationException {
//		log.info("LogIn ad User in controllerPackage...");
//		String user= asi.logInUserDetails(loginDetails);
//		return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
//	}
//	@PostMapping("/login/host")
//	public ResponseEntity<Host> logInHostDetails(@RequestBody @Valid LoginClass loginDetails) throws ApplicationException {
//		log.info("LogIn ad Host in controllerPackage...");
//		Host host =asi.logInHostDetails(loginDetails);
//		return new ResponseEntity<>(host,HttpStatus.ACCEPTED);
//	}
//	@PostMapping("/login/admin")
//	public ResponseEntity<Admin> logInAdminDetails(@RequestBody @Valid LoginClass loginDetails) throws ApplicationException {
//		log.info("LogIn ad Admin in controllerPackage...");
//		Admin admin =asi.logInAdminDetails(loginDetails);
//		return new ResponseEntity<>(admin,HttpStatus.ACCEPTED);
//	}
	@GetMapping("/logIn")
	public ResponseEntity<Object> logInUserDetails(Authentication auth) throws ApplicationException {
		log.info("LogIn in controllerPackage...");
		if(auth.getName().toLowerCase().contains("@roomonboat.com")){
			Admin admin = asi.logInAdminDetails(auth);
			return new ResponseEntity<>(admin,HttpStatus.ACCEPTED);
		}else if(auth.getName().toLowerCase().contains("@host.com")){
			Host host = asi.logInHostDetails(auth);
			return new ResponseEntity<>(host,HttpStatus.ACCEPTED);
		}else{
			Users user = asi.logInUserDetails(auth);
			return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
		}
	}
}
