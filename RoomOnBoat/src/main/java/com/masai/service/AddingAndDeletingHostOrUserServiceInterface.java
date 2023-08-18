package com.masai.service;

import java.util.List;

//import org.springframework.security.core.Authentication;

//import org.springframework.security.core.Authentication;
import com.masai.exception.ApplicationException;
import com.masai.model.Host;
import com.masai.model.Users;

public interface AddingAndDeletingHostOrUserServiceInterface {

	Host addHost(Host host) throws ApplicationException;

	Users addUser(Users user) throws ApplicationException;

	Users deleteUser(Integer userId) throws ApplicationException;

	Host deleteHost(Integer hostId) throws ApplicationException;

	List<Users> getAllUser() throws ApplicationException;

	List<Host> getAllHost() throws ApplicationException;

//	Users logInUserDetails(Authentication auth) throws ApplicationException;
//
//	Host logInHostDetails(Authentication auth) throws ApplicationException;

}
