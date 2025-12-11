package com.temple.temple_database.service;

import com.temple.temple_database.dto.HallRequest;
import com.temple.temple_database.model.Hall;
import com.temple.temple_database.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HallService {
    
    @Autowired
    private HallRepository hallRepository;
    
    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }
    
    public Optional<Hall> getHallById(Long id) {
        return hallRepository.findById(id);
    }
    
    public Hall createHall(HallRequest request) {
        if (hallRepository.existsByHallName(request.getHallName())) {
            throw new RuntimeException("Hall with name " + request.getHallName() + " already exists");
        }
        
        Hall hall = new Hall();
        hall.setHallName(request.getHallName());
        hall.setHallDetails(request.getHallDetails());
        
        return hallRepository.save(hall);
    }
    
    public Hall updateHall(Long id, HallRequest request) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hall not found with id: " + id));
        
        if (!hall.getHallName().equals(request.getHallName()) && 
            hallRepository.existsByHallName(request.getHallName())) {
            throw new RuntimeException("Hall with name " + request.getHallName() + " already exists");
        }
        
        hall.setHallName(request.getHallName());
        hall.setHallDetails(request.getHallDetails());
        
        return hallRepository.save(hall);
    }
    
    public void deleteHall(Long id) {
        if (!hallRepository.existsById(id)) {
            throw new RuntimeException("Hall not found with id: " + id);
        }
        hallRepository.deleteById(id);
    }
}





