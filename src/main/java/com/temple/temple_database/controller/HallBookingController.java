package com.temple.temple_database.controller;

import com.temple.temple_database.dto.HallBookingRequest;
import com.temple.temple_database.model.HallBooking;
import com.temple.temple_database.service.HallBookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hall-bookings")
@CrossOrigin(origins = "*")
public class HallBookingController {
    
    @Autowired
    private HallBookingService hallBookingService;
    
    @GetMapping
    public ResponseEntity<List<HallBooking>> getAllBookings() {
        return ResponseEntity.ok(hallBookingService.getAllBookings());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<HallBooking> getBookingById(@PathVariable Long id) {
        return hallBookingService.getBookingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<HallBooking> createBooking(@Valid @RequestBody HallBookingRequest request) {
        try {
            HallBooking booking = hallBookingService.createBooking(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(booking);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<HallBooking> updateBooking(@PathVariable Long id, @Valid @RequestBody HallBookingRequest request) {
        try {
            HallBooking booking = hallBookingService.updateBooking(id, request);
            return ResponseEntity.ok(booking);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        try {
            hallBookingService.deleteBooking(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}




