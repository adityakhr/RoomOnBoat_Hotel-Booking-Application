package com.masai.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.masai.model.Admin;
import com.masai.model.Host;
import com.masai.model.Users;
import com.masai.repository.AdminRepository;
import com.masai.repository.HostRepository;
import com.masai.repository.UsersRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class UserDetailsServiceClass implements UserDetailsService  {
	@Autowired
	private UsersRepository uRepo;
	@Autowired
	private HostRepository hRepo;
	@Autowired
	private AdminRepository aRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		String[] splitString = username.trim().split("@");
		String differenciator=splitString[1].trim();
		if(differenciator.toLowerCase().equals("roomonboat.com")) {
			log.info("Checking for Admin Creds in UserDetails...");
			Optional<Admin> opt = aRepo.findByEmail(username);
			if (opt.isEmpty()) {
				log.info("No Admin Creds Found...");
				throw new BadCredentialsException("No details found with this username: " + username);
			}
			log.info("Admin Email Found...");
			Admin admin = opt.get();
			return new User(admin.getEmail(), admin.getPassword(), authorityGeneration(admin.getRole()));
		}else if(differenciator.toLowerCase().equals("host.com")) {
			log.info("Checking for Host Creds in UserDetails...");
	        Optional<Host> opt = hRepo.findByEmail(username);
			if (opt.isEmpty()) {
		    	log.info("No Host Creds Found...");
		        throw new BadCredentialsException("No details found with this username: " + username);
		    }
		    log.info("Host Email Found...");
	        Host host = opt.get();
	        return new User(host.getEmail(), host.getPassword(), authorityGeneration(host.getRole()));
		}else{
			log.info("Checking for User Creds in UserDetails...");
			Optional<Users> opt = uRepo.findByEmail(username);
			if (opt.isEmpty()) {
				log.info("No User Email Found...");
				throw new BadCredentialsException("No details found with this username: " + username);
			}
			log.info("User Creds Found...");
			Users user = opt.get();
			return new User(user.getEmail(), user.getPassword(), authorityGeneration(user.getRole()));
		}
	}

	private Collection<? extends GrantedAuthority> authorityGeneration(String role) {
		List<GrantedAuthority>gAutho = new ArrayList<>();	
		SimpleGrantedAuthority sGrAutho = new SimpleGrantedAuthority(role);
		gAutho.add(sGrAutho);
		return gAutho;
	}
}
