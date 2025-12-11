package com.temple.temple_database.service;

import com.temple.temple_database.dto.HallBookingRequest;
import com.temple.temple_database.model.Hall;
import com.temple.temple_database.model.HallBooking;
import com.temple.temple_database.repository.HallBookingRepository;
import com.temple.temple_database.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HallBookingService {
    
    @Autowired
    private HallBookingRepository hallBookingRepository;
    
    @Autowired
    private HallRepository hallRepository;
    
    public List<HallBooking> getAllBookings() {
        return hallBookingRepository.findAll();
    }
    
    public Optional<HallBooking> getBookingById(Long id) {
        return hallBookingRepository.findById(id);
    }
    
    public HallBooking createBooking(HallBookingRequest request) {
        Hall hall = hallRepository.findById(request.getHallId())
                .orElseThrow(() -> new RuntimeException("Hall not found with id: " + request.getHallId()));
        
        HallBooking booking = new HallBooking();
        booking.setName(request.getName());
        booking.setPhoneNo(request.getPhoneNo());
        booking.setDetails(request.getDetails());
        booking.setPrice(request.getPrice());
        booking.setDate(request.getDate());
        booking.setHall(hall);
        
        return hallBookingRepository.save(booking);
    }
    
    public HallBooking updateBooking(Long id, HallBookingRequest request) {
        HallBooking booking = hallBookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
        
        Hall hall = hallRepository.findById(request.getHallId())
                .orElseThrow(() -> new RuntimeException("Hall not found with id: " + request.getHallId()));
        
        booking.setName(request.getName());
        booking.setPhoneNo(request.getPhoneNo());
        booking.setDetails(request.getDetails());
        booking.setPrice(request.getPrice());
        booking.setDate(request.getDate());
        booking.setHall(hall);
        
        return hallBookingRepository.save(booking);
    }
    
    public void deleteBooking(Long id) {
        if (!hallBookingRepository.existsById(id)) {
            throw new RuntimeException("Booking not found with id: " + id);
        }
        hallBookingRepository.deleteById(id);
    }
}





