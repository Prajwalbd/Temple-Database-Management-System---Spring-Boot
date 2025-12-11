package com.temple.temple_database.controller;

import com.temple.temple_database.dto.VolunteerRequest;
import com.temple.temple_database.model.Volunteer;
import com.temple.temple_database.service.VolunteerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/volunteers")
@CrossOrigin(origins = "*")
public class VolunteerController {
    
    @Autowired
    private VolunteerService volunteerService;
    
    @GetMapping
    public ResponseEntity<List<Volunteer>> getAllVolunteers() {
        return ResponseEntity.ok(volunteerService.getAllVolunteers());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Volunteer> getVolunteerById(@PathVariable Long id) {
        return volunteerService.getVolunteerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Volunteer> createVolunteer(@Valid @RequestBody VolunteerRequest request) {
        Volunteer volunteer = volunteerService.createVolunteer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(volunteer);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Volunteer> updateVolunteer(@PathVariable Long id, @Valid @RequestBody VolunteerRequest request) {
        try {
            Volunteer volunteer = volunteerService.updateVolunteer(id, request);
            return ResponseEntity.ok(volunteer);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVolunteer(@PathVariable Long id) {
        try {
            volunteerService.deleteVolunteer(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}





