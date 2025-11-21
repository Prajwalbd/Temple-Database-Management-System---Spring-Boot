package com.temple.temple_database.service;

import com.temple.temple_database.dto.VolunteerRequest;
import com.temple.temple_database.model.Volunteer;
import com.temple.temple_database.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VolunteerService {
    
    @Autowired
    private VolunteerRepository volunteerRepository;
    
    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }
    
    public Optional<Volunteer> getVolunteerById(Long id) {
        return volunteerRepository.findById(id);
    }
    
    public Volunteer createVolunteer(VolunteerRequest request) {
        Volunteer volunteer = new Volunteer();
        volunteer.setVolunteerName(request.getVolunteerName());
        volunteer.setVolunteerPhone(request.getVolunteerPhone());
        volunteer.setVolunteerDetail(request.getVolunteerDetail());
        volunteer.setDate(request.getDate());
        
        return volunteerRepository.save(volunteer);
    }
    
    public Volunteer updateVolunteer(Long id, VolunteerRequest request) {
        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Volunteer not found with id: " + id));
        
        volunteer.setVolunteerName(request.getVolunteerName());
        volunteer.setVolunteerPhone(request.getVolunteerPhone());
        volunteer.setVolunteerDetail(request.getVolunteerDetail());
        volunteer.setDate(request.getDate());
        
        return volunteerRepository.save(volunteer);
    }
    
    public void deleteVolunteer(Long id) {
        if (!volunteerRepository.existsById(id)) {
            throw new RuntimeException("Volunteer not found with id: " + id);
        }
        volunteerRepository.deleteById(id);
    }
}




