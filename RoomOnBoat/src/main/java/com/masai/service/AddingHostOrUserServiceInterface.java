package com.masai.service;


//import org.springframework.security.core.Authentication;

//import org.springframework.security.core.Authentication;
import com.masai.exception.ApplicationException;
import com.masai.model.Host;
import com.masai.model.Users;

public interface AddingHostOrUserServiceInterface {

	public Host addHost(Host host) throws ApplicationException;

	public Users addUser(Users user) throws ApplicationException;

//	Users logInUserDetails(Authentication auth) throws ApplicationException;
//
//	Host logInHostDetails(Authentication auth) throws ApplicationException;

}
