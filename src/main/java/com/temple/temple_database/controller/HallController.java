package com.temple.temple_database.controller;

import com.temple.temple_database.dto.HallRequest;
import com.temple.temple_database.model.Hall;
import com.temple.temple_database.service.HallService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/halls")
@CrossOrigin(origins = "*")
public class HallController {
    
    @Autowired
    private HallService hallService;
    
    @GetMapping
    public ResponseEntity<List<Hall>> getAllHalls() {
        return ResponseEntity.ok(hallService.getAllHalls());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Hall> getHallById(@PathVariable Long id) {
        return hallService.getHallById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Hall> createHall(@Valid @RequestBody HallRequest request) {
        try {
            Hall hall = hallService.createHall(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(hall);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Hall> updateHall(@PathVariable Long id, @Valid @RequestBody HallRequest request) {
        try {
            Hall hall = hallService.updateHall(id, request);
            return ResponseEntity.ok(hall);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHall(@PathVariable Long id) {
        try {
            hallService.deleteHall(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}





