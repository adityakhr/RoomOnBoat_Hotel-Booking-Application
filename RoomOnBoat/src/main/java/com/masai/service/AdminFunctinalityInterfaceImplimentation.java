package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.masai.exception.ApplicationException;
import com.masai.model.Admin;
import com.masai.model.Host;
import com.masai.model.Property;
import com.masai.model.Room;
import com.masai.model.Users;
import com.masai.repository.AdminRepository;
import com.masai.repository.HostRepository;
import com.masai.repository.PropertyRepository;
import com.masai.repository.RoomRepository;
import com.masai.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminFunctinalityInterfaceImplimentation implements AdminFunctionalityInterface {
	@Autowired
	private HostRepository hRepo;
	@Autowired
	private UsersRepository uRepo;
	@Autowired
	private RoomRepository rRepo;
	@Autowired
	private PropertyRepository pRepo;
	@Autowired
	private AdminRepository aRepo;
	@Autowired
	private PasswordEncoder pass;
	
	@Override
	public Users deleteUser(Integer userId) throws ApplicationException {
		log.info("Admin Deleting User in Service");
		Optional<Users> opt = uRepo.findById(userId);
		if(opt.isEmpty()) {
			throw new ApplicationException("Host with these credentials is not valid...");
		}
		uRepo.delete(opt.get());
		return opt.get();
		
	}
	@Override
	public Host deleteHost(Integer hostId) throws ApplicationException {
		log.info("Admin Deleting Host in Service");
		Optional<Host> opt = hRepo.findById(hostId);
		if(opt.isEmpty()) {
			throw new ApplicationException("Host with these credentials is not valid...");
		}
		hRepo.delete(opt.get());
		return opt.get();
	}
	@Override
	public List<Host> getAllHost(int page, int count, String order) throws ApplicationException {
		log.info("Admin is Getting Hosts in Service");
		Sort sort=null;
		if(order.toLowerCase().equals("desc")) {
			sort =Sort.by("name").descending();
		}else {
			sort =Sort.by("name").ascending();
		}
		Pageable pageNumber = PageRequest.of(page, count,sort);
		
		Page<Host>hostsOdPage = hRepo.findAll(pageNumber);
		List<Host>hosts=hostsOdPage.getContent();
		if(hosts==null|| hosts.size()==0) {
			throw new ApplicationException("Hosts Not Found...");
		}
		return hosts;
	}
	@Override
	public List<Admin> getAlladmin(Integer page, Integer count, String order) throws ApplicationException {
		log.info("Admin is Getting Admin in Service");
		Sort sort=null;
		if(order.toLowerCase().equals("desc")) {
			sort =Sort.by("name").descending();
		}else {
			sort =Sort.by("name").ascending();
		}
		Pageable pageNumber = PageRequest.of(page, count,sort);
		
		Page<Admin>adminsOdPage = aRepo.findAll(pageNumber);
		List<Admin>admins=adminsOdPage.getContent();
		if(admins==null|| admins.size()==0) {
			throw new ApplicationException("Hosts Not Found...");
		}
		return admins;
	}
	@Override
	public List<Users> getAllUser(int page, int count, String order) throws ApplicationException{
		log.info("Admin is Getting Users in Service");
		Sort sort=null;
		if(order.toLowerCase().equals("desc")) {
			sort =Sort.by("name").descending();
		}else {
			sort =Sort.by("name").ascending();
		}
		Pageable pageNumber = PageRequest.of(page, count,sort);
		Page<Users>usersOfPage = uRepo.findAll(pageNumber);
		List<Users>users = usersOfPage.getContent();
		if(users==null|| users.size()==0) {
			throw new ApplicationException("Users Not Found...");
		}
		return users;
	}
	@Override
	public Property deleteProperty(Integer propertyId) throws ApplicationException {
		log.info("Admin is Deleting Property in Service");
		Optional<Property> opt = pRepo.findById(propertyId);
		if(opt.isEmpty()) {
			throw new ApplicationException("Property Not Found...");
		}
		pRepo.delete(opt.get());
		return opt.get();
	}
	@Override
	public Room deleteRoom(Integer roomId) throws ApplicationException {
		log.info("Admin is Deleting Room in Service");
		Optional<Room> opt = rRepo.findById(roomId);
		if(opt.isEmpty()) {
			throw new ApplicationException("Room Not Found...");
		}
		rRepo.delete(opt.get());
		return opt.get();
	}
	@Override
	public List<Property> getAllProperties(int page, int count, String order) throws ApplicationException {
		log.info("Admin is Getting All Properties in Service");
		Sort sort=null;
		if(order.toLowerCase().equals("desc")) {
			sort =Sort.by("name").descending();
		}else {
			sort =Sort.by("name").ascending();
		}
		Pageable pageNumber = PageRequest.of(page, count,sort);
		Page<Property>propertyOfPage = pRepo.findAll(pageNumber);
		List<Property>properties = propertyOfPage.getContent();
		if(properties==null|| properties.size()==0) {
			throw new ApplicationException("Properties Not Found...");
		}
		return properties;
	}
	@Override
	public List<Room> getAllRooms(int page, int count, String order) throws ApplicationException{
		log.info("Admin is Getting All Rooms in Service");
		Sort sort=null;
		if(order.toLowerCase().equals("desc")) {
			sort =Sort.by("name").descending();
		}else {
			sort =Sort.by("name").ascending();
		}
		Pageable pageNumber = PageRequest.of(page, count,sort);
		Page<Room>roomOfPage = rRepo.findAll(pageNumber);
		List<Room>rooms = roomOfPage.getContent();
		if(rooms==null|| rooms.size()==0) {
			throw new ApplicationException("Rooms Not Found...");
		}
		return rooms;
	}
	@Override
	public Admin addHostAdmin(Integer hostId) throws ApplicationException {
		log.info("Admin is Adding Host to Admin in Service...");
		Optional<Host> host=hRepo.findById(hostId);
		if(host.isEmpty()) {
			throw new ApplicationException("No Host Found...");
		}
		Optional<Admin> opt = aRepo.findByEmail(host.get().getEmail());
		if(opt.isPresent()) {
			throw new ApplicationException("Already Exists with these credentials...");
		}
		Admin ad = new Admin();
		ad.setEmail(host.get().getEmail());
		ad.setName(host.get().getName());
		ad.setPassword(pass.encode(host.get().getPassword()));
//		ad.setPassword(host.get().getPassword());
		aRepo.save(ad);
		hRepo.delete(host.get());
		return ad;
	}
	@Override
	public Admin addUserAdmin(Integer userId) throws ApplicationException {
		log.info("Admin is Adding User to Admin in Service...");
		Optional<Users> user=uRepo.findById(userId);
		if(user.isEmpty()) {
			throw new ApplicationException("No User Found...");
		}
		Optional<Admin> opt = aRepo.findByEmail(user.get().getEmail());
		if(opt.isPresent()) {
			throw new ApplicationException("Already Exists with these credentials...");
		}
		Admin ad = new Admin();
		ad.setEmail(user.get().getEmail());
		ad.setName(user.get().getName());
		ad.setPassword(pass.encode(user.get().getPassword()));
//		ad.setPassword(user.get().getPassword());
		aRepo.save(ad);
		uRepo.delete(user.get());
		return ad;
	}
	
	
}
