package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.masai.exception.ApplicationException;
import com.masai.model.Admin;
import com.masai.model.Host;
import com.masai.model.Users;
import com.masai.repository.HostRepository;
import com.masai.repository.UsersRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class AddingHostOrUserServiceInterfaceImplimentation implements AddingHostOrUserServiceInterface {
	@Autowired
	private HostRepository hRepo;
	@Autowired
	private UsersRepository uRepo;
	@Autowired
	private UserDetailsServiceClass uds;
	@Autowired
	private PasswordEncoder pass;
	
	@Override
	public Host addHost(@Valid Host host) throws ApplicationException {
		log.info("Adding new Host in Service");
		Optional<Host> opt = hRepo.findById(host.getHostId());
		if(opt.isPresent()) {
			throw new ApplicationException("Already Exists with these credentials...");
		}
		if(host.getHostId()!=0) {
			throw new ApplicationException("Id is AutoGenerated Type Don't mention it...");
		}
		Optional<Host> opt1 = hRepo.findByEmail(host.getEmail());
		if(opt1.isPresent()) {
			throw new ApplicationException("Email Already Exists...");
		}
		host.setPassword(pass.encode(host.getPassword()));
		hRepo.save(host);
		return host;
	}
	@Override
	public Users addUser(Users user) throws ApplicationException{
		log.info("Adding new User in Service");
		Optional<Users> opt = uRepo.findById(user.getUserId());
		if(opt.isPresent()) {
			throw new ApplicationException("Already Exists with these credentials...");
		}
		if(user.getUserId()!=0) {
			throw new ApplicationException("Id is AutoGenerated Type Don't mention it...");
		}
		Optional<Users> opt1 = uRepo.findByEmail(user.getEmail());
		if(opt1.isPresent()) {
			throw new ApplicationException("Email Already Exists...");
		}
		user.setPassword(pass.encode(user.getPassword()));
		uRepo.save(user);
		return user;
	}
	
	@Override
	public Users logInUserDetails(Authentication auth) throws ApplicationException {
		log.info("Checking for User in Service");
		if(auth==null) {
			throw new ApplicationException("No User Found with these Credentials");
		} 
		Users user = (Users) uds.loadUserByUsername(auth.getName()+":user");
		return user;
	}
	@Override
	public Host logInHostDetails(Authentication auth) throws ApplicationException {
		log.info("Checking for Host in Service");
		if(auth==null) {
			throw new ApplicationException("No Host Found with these Credentials");
		}
		Host host = (Host) uds.loadUserByUsername(auth.getName()+":host");
		return host;
	}
	@Override
	public Admin logInAdminDetails(Authentication auth) throws ApplicationException {
		log.info("Checking for Admin in Service");
		if(auth==null) {
			throw new ApplicationException("No Admin Found with these Credentials");
		}
		Admin admin = (Admin) uds.loadUserByUsername(auth.getName()+":admin");
		return admin;
	}
	
	
}
