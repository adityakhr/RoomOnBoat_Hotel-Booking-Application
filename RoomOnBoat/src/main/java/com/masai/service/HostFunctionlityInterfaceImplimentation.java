package com.masai.service;
import java.util.ArrayList;
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
import com.masai.model.Booking;
import com.masai.model.Host;
import com.masai.model.Property;
import com.masai.model.Room;
import com.masai.repository.BookingRepository;
import com.masai.repository.HostRepository;
import com.masai.repository.PropertyRepository;
import com.masai.repository.RoomRepository;
import com.masai.update.UpdateName;
import com.masai.update.UpdatePassword;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class HostFunctionlityInterfaceImplimentation implements HostFunctinalityInterface{
	@Autowired
	private HostRepository hRepo;
	@Autowired
	private PropertyRepository pRepo;
	@Autowired
	private RoomRepository rRepo;
	@Autowired
	private BookingRepository bRepo;
	@Autowired
	private PasswordEncoder pass;
	
	@Override
	public Property addNewProperty(Property property, Integer hostId) throws ApplicationException {
		log.info("Host is Adding Property in Service...");
		Optional<Property> opt = pRepo.findById(property.getPropertyId());
		Optional<Host> opt1 = hRepo.findById(hostId);
		if(opt1.isEmpty()) {
			throw new ApplicationException("Host Not Found...");
		}
		if(opt.isPresent()) {
			throw new ApplicationException("Id Already Exists...");
		}
		if(property.getPropertyId()!=0) {
			throw new ApplicationException("Id Is AutoGenerated Type Don't Mention It...");
		}
		property.setHost(opt1.get());
		List<Property> properties= opt1.get().getProperty();
		if(properties==null) {
			properties=new ArrayList<>();
		}
		properties.add(property);
		opt1.get().setProperty(properties);
		pRepo.save(property);
		return property;
	}
	
	@Override
	public Room addRoomToProperty(Room room, Integer propertyId, Integer hostId) throws ApplicationException {
		log.info("Host is Adding Property's new Room in Service...");
		Optional<Host> optt = hRepo.findById(hostId);
		if(optt.isEmpty()) {
			throw new ApplicationException("Host Not Found...");
		}
		Optional<Property> opt1 = pRepo.findById(propertyId);
		Optional<Room> opt = rRepo.findById(room.getRoomId());
		if(opt1.isEmpty()) {
			throw new ApplicationException("Property Not Found...");
		}
		if(opt.isPresent()) {
			throw new ApplicationException("Id Already Exists...");
		}
		if(room.getRoomId()!=0) {
			throw new ApplicationException("Id Is AutoGenerated Type Don't Mention It...");
		}
		if(optt.get().getHostId()!=opt1.get().getHost().getHostId()){
			throw new ApplicationException("This Property Doesn't Belong To This Host...");
		}
		room.setProperty(opt1.get());
		List<Room> rooms= opt1.get().getRooms();
		if(rooms==null) {
			rooms=new ArrayList<>();
		}
		rooms.add(room);
		room.setStatus(room.getStatus().toLowerCase());
		opt1.get().setRooms(rooms);
		rRepo.save(room);
		return room;
	}
	
	
	
	
	
	@Override
	public List<Room> fetchAllRooms(Integer propertyId, Integer hostId, Integer page, Integer count, String order) throws ApplicationException {
		log.info("Host is Getting All Rooms in Service...");
		Optional<Host> opt1 = hRepo.findById(hostId);
		if(opt1.isEmpty()) {
			throw new ApplicationException("Host Not Found...");
		}
		Optional<Property> property = pRepo.findById(propertyId);
		if(property.isEmpty()) {
			throw new ApplicationException("Property Not Found...");
		}
		
		if(property.get().getHost().getHostId()!=hostId) {
			throw new ApplicationException("Property Doesn't Belong To This Host");
		}
		Sort sort=null;
		if(order.toLowerCase().equals("desc")) {
			sort =Sort.by("name").descending();
		}else {
			sort =Sort.by("name").ascending();
		}
		Pageable pageNumber = PageRequest.of(page, count,sort);
		Page<Room>roomsOfPage = rRepo.findRoomForHost(propertyId,pageNumber);
		List<Room>rooms = roomsOfPage.getContent();
		if(rooms==null || rooms.isEmpty()) {
			throw new ApplicationException("Property Does Not Have Rooms...");
		}
		return rooms;
	}
    @Override
	public List<Property> fetchAllProperties(Integer hostId, Integer page, Integer count, String order) throws ApplicationException {
    	log.info("Host is Getting All Properties in Service...");
    	Optional<Host> opt1 = hRepo.findById(hostId);
		if(opt1.isEmpty()) {
			throw new ApplicationException("Host Not Found...");
		}
		Sort sort=null;
		if(order.toLowerCase().equals("desc")) {
			sort =Sort.by("name").descending();
		}else {
			sort =Sort.by("name").ascending();
		}
		Pageable pageNumber = PageRequest.of(page, count,sort);
		Page<Property>propertyOfPage = pRepo.findPropertyForHost(hostId,pageNumber);
		List<Property> properties = propertyOfPage.getContent();
		if(properties==null || properties.isEmpty()) {
			throw new ApplicationException("Property Not Found...");
		}
		return properties;
	}
    @Override
	public Host deleteYourAccount(Integer hostId) throws ApplicationException {
    	log.info("Host is Deleting his account in Service...");
    	Optional<Host> opt1 = hRepo.findById(hostId);
		if(opt1.isEmpty()) {
			throw new ApplicationException("Host Not Found...");
		}
		Host host = opt1.get();
		hRepo.delete(opt1.get());
		return host;
	}

    @Override
	public Host updatePassword(Integer hostId, UpdatePassword updatedPassword) throws ApplicationException {
    	log.info("Host is Updating Password in Service...");
    	Optional<Host> opt1 = hRepo.findById(hostId);
		if(opt1.isEmpty()) {
			throw new ApplicationException("Host Not Found...");
		}
		opt1.get().setPassword(pass.encode(updatedPassword.getPassword()));
		hRepo.save(opt1.get());
		return opt1.get();
	}
    @Override
	public Host updateName(Integer hostId, UpdateName updatedName) throws ApplicationException {
    	log.info("Host is Updating Name in Service...");
    	Optional<Host> opt1 = hRepo.findById(hostId);
		if(opt1.isEmpty()) {
			throw new ApplicationException("Host Not Found...");
		}
		opt1.get().setName(updatedName.getName());
		opt1.get().setLastName(updatedName.getLastName());
		hRepo.save(opt1.get());
		return opt1.get();
	}
    @Override
	public Room deleteRoom(Integer hostId,Integer propertyId,Integer roomId) throws ApplicationException {
    	log.info("Host is Deleting Property's Room in Service...");
    	Optional<Room> opt = rRepo.findById(roomId);
		if(opt.isEmpty()) {
			throw new ApplicationException("Room not Found...");
		}
		Optional<Host> opt1 = hRepo.findById(hostId);
		if(opt1.isEmpty()) {
			throw new ApplicationException("Host Not Found...");
		}
		Optional<Property> property = pRepo.findById(propertyId);
		if(property.isEmpty()) {
			throw new ApplicationException("Property Not Found...");
		}
		if(property.get().getHost().getHostId()!= opt1.get().getHostId()) {
			throw new ApplicationException("It Does Not Belong To This Host...");
		}
		if(property.get().getPropertyId()!=opt.get().getProperty().getPropertyId()) {
			throw new ApplicationException("Room Does Not Belong To This Property...");
		}
		Room room =opt.get() ;
		rRepo.delete(opt.get());
		return room;
	}
	@Override
	public Property deleteProperty(Integer hostId,Integer propertyId) throws ApplicationException {
		log.info("Host is Deleting Property in Service...");
		Optional<Host> opt1 = hRepo.findById(hostId);
		if(opt1.isEmpty()) {
			throw new ApplicationException("Host Not Found...");
		}
		Optional<Property> property = pRepo.findById(propertyId);
		if(property.isEmpty()) {
			throw new ApplicationException("Property Not Found...");
		}
		if(property.get().getHost().getHostId()!= opt1.get().getHostId()) {
			throw new ApplicationException("It Does Not Belong To This Host...");
		}
		Property prop =property.get();
		pRepo.delete(property.get());
		return prop;
	}
	@Override
	public Booking updateBookingStatus(Integer hostId, Integer bookingId) throws ApplicationException {
		log.info("Host is Updating Room Status in Service...");
		Optional<Host> opt1 = hRepo.findById(hostId);
		if(opt1.isEmpty()) {
			throw new ApplicationException("Host Not Found...");
		}
		Optional<Booking> booking = bRepo.findById(bookingId);
		if(booking.isEmpty()) {
			throw new ApplicationException("Booking Not Found...");
		}
		List<Room>rooms=booking.get().getRooms();
		if(rooms==null || rooms.isEmpty()) {
			throw new ApplicationException("Rooms Not Found...");
		}
		for(Room room : rooms) {
			room.setStatus("Available");
			room.setBooking(null);
		}
		booking.get().setRooms(rooms);
		bRepo.save(booking.get());
		return booking.get();
	}
	
	
}
