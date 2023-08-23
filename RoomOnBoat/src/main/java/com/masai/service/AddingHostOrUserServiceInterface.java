package com.masai.service;

import com.masai.exception.ApplicationException;
import com.masai.model.Admin;
import com.masai.model.Host;
import com.masai.model.Users;
import com.masai.update.LoginClass;
import org.springframework.security.core.Authentication;

public interface AddingHostOrUserServiceInterface {

	public Host addHost(Host host) throws ApplicationException;

	public Users addUser(Users user) throws ApplicationException;

	Host logInHostDetails(Authentication auth) throws ApplicationException;
	
	Admin logInAdminDetails(Authentication auth) throws ApplicationException;

	Users logInUserDetails(Authentication auth) throws ApplicationException;

}
