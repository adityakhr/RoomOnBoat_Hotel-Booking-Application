package com.masai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.masai.exception.ApplicationException;
import com.masai.model.Booking;
import com.masai.model.Property;
import com.masai.model.Room;
import com.masai.model.Users;
import com.masai.repository.BookingRepository;
import com.masai.repository.PropertyRepository;
import com.masai.repository.RoomRepository;
import com.masai.repository.UsersRepository;
import com.masai.update.UpdateEmail;
import com.masai.update.UpdateName;
import com.masai.update.UpdatePassword;
@Service
public class UserFunctionalityInterfaceImplementation implements UserFunctionalityInterface {
	@Autowired
	private UsersRepository uRepo;
	@Autowired
	private RoomRepository rRepo;
	@Autowired
	private PropertyRepository pRepo;
	@Autowired
	private BookingRepository bRepo;
//	@Autowired
//	private PasswordEncoder pass;
	@Override
	public List<Property> getAllProperties(int page, int count, String order) throws ApplicationException {
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
	public List<Room> getRooms(Integer propertyId, Integer page, Integer count, String order) throws ApplicationException {
		Sort sort=null;
		if(order.toLowerCase().equals("desc")) {
			sort =Sort.by("name").descending();
		}else {
			sort =Sort.by("name").ascending();
		}
		Pageable pageNumber = PageRequest.of(page, count,sort);
		Page<Room>roomOfPage = rRepo.findRoom(propertyId,pageNumber);
		List<Room>rooms = roomOfPage.getContent();
		if(rooms==null|| rooms.size()==0) {
			throw new ApplicationException("Rooms Not Found...");
		}
		return rooms;
	}
	public List<Booking> getBookings(Integer userId, Integer page, Integer count, String order) throws ApplicationException{
		Sort sort=null;
		if(order.toLowerCase().equals("desc")) {
			sort =Sort.by("name").descending();
		}else {
			sort =Sort.by("name").ascending();
		}
		Pageable pageNumber = PageRequest.of(page, count,sort);
		Page<Booking>bookingOfPage = bRepo.findBooking(userId,pageNumber);
		List<Booking>rooms = bookingOfPage.getContent();
		if(rooms==null|| rooms.size()==0) {
			throw new ApplicationException("Rooms Not Found...");
		}
		return rooms;
	}
	@Override
	public Users updateEmail(Integer userId, UpdateEmail updatedEmail) throws ApplicationException {
		Optional<Users> opt1 = uRepo.findById(userId);
		if(opt1.isEmpty()) {
			throw new ApplicationException("User Not Found...");
		}
		opt1.get().setEmail(updatedEmail.getEmail());
		uRepo.save(opt1.get());
		return opt1.get();
	}
	@Override
	public Users updatePassword(Integer userId, UpdatePassword updatedPassword) throws ApplicationException {
		Optional<Users> opt1 = uRepo.findById(userId);
		if(opt1.isEmpty()) {
			throw new ApplicationException("User Not Found...");
		}
//		opt1.get().setPassword(pass.encode(updatedPassword.getPassword()));
		opt1.get().setPassword(updatedPassword.getPassword());
		uRepo.save(opt1.get());
		return opt1.get();
	}
	@Override
	public Users updateName(Integer userId, UpdateName updatedName) throws ApplicationException {
		Optional<Users> opt1 = uRepo.findById(userId);
		if(opt1.isEmpty()) {
			throw new ApplicationException("User Not Found...");
		}
		opt1.get().setName(updatedName.getName());
		uRepo.save(opt1.get());
		return opt1.get();
	}
	@Override
	public Users deleteYourAccount(Integer userId) throws ApplicationException {
		Optional<Users> opt1 = uRepo.findById(userId);
		if(opt1.isEmpty()) {
			throw new ApplicationException("User Not Found...");
		}
		Users user = opt1.get();
		uRepo.delete(opt1.get());
		return user;
	}
	@Override
	public Room bookRoom(Integer userId,Integer roomId) throws ApplicationException {
		Optional<Users> opt1 = uRepo.findById(userId);
		if(opt1.isEmpty()) {
			throw new ApplicationException("User Not Found...");
		}
		Optional<Room> opt = rRepo.findById(roomId);
		if(opt.isEmpty()) {
			throw new ApplicationException("Room Not Found...");
		}
		if(!opt.get().getStatus().toLowerCase().equals("available")) {
			throw new ApplicationException("Room is not available...");
		}
		Booking booking=bRepo.findbooking(userId);
		if(booking==null) {
			booking=new Booking();
			
		}
		List<Room>rooms=booking.getRooms();
		if(rooms==null|| rooms.size()==0) {
			rooms=new ArrayList<>();
		}
		rooms.add(opt.get());
		booking.setRooms(rooms);
		opt.get().setBooking(booking);
		booking.setUser(opt1.get());
		List<Booking>userBooking=opt1.get().getBookings();
		if(userBooking==null||userBooking.size()==0) {
			userBooking=new ArrayList<>();
		}
		for(Booking b : userBooking) {
			if(b.getActive()) {
				userBooking.remove(b);
			}
		}
		userBooking.add(booking);
		bRepo.save(booking);
		return opt.get();
	}
	@Override
	public Room deleteBookedRoom(Integer userId, Integer bookingId, Integer roomId) throws ApplicationException {
		Optional<Users> opt1 = uRepo.findById(userId);
		if(opt1.isEmpty()) {
			throw new ApplicationException("User Not Found...");
		}
		Optional<Room> opt = rRepo.findById(roomId);
		if(opt.isEmpty()) {
			throw new ApplicationException("Room Not Found...");
		}
		Optional<Booking> opt2 = bRepo.findById(bookingId);
		if(opt2.isEmpty()) {
			throw new ApplicationException("Booking Not Found...");
		}
		if(opt.get().getBooking().getBookingId()!=bookingId) {
			throw new ApplicationException("This Room Does Not Belong To This Booking...");
		}
		if(opt2.get().getUser().getUserId()!=userId) {
			throw new ApplicationException("This User Does Not Belong To This Booking...");
		}
		Booking booking=bRepo.findbooking(userId);
		booking.getRooms().remove(opt.get());
		opt.get().setStatus("available");
		rRepo.save(opt.get());
		bRepo.save(booking);
		return opt.get();
	}
	@Override
	public Booking confirmBooking(Integer userId, Integer bookingId) throws ApplicationException {
		Optional<Users> opt1 = uRepo.findById(userId);
		if(opt1.isEmpty()) {
			throw new ApplicationException("User Not Found...");
		}
		Optional<Booking> opt2 = bRepo.findById(bookingId);
		if(opt2.isEmpty()) {
			throw new ApplicationException("Booking Not Found...");
		}
		for(Room r : opt2.get().getRooms()) {
			if(r.getStatus().toLowerCase().equals("unavailable")) {
				throw new ApplicationException("May One Of Room Has Taken...");
			}
			r.setStatus("unavailable");
		}
		opt2.get().setActive(false);
		bRepo.save(opt2.get());
		return opt2.get();
	}
	

}
