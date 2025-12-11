package com.temple.temple_database.service;

import com.temple.temple_database.dto.PoojaRequest;
import com.temple.temple_database.model.Pooja;
import com.temple.temple_database.repository.PoojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoojaService {
    
    @Autowired
    private PoojaRepository poojaRepository;
    
    public List<Pooja> getAllPoojas() {
        return poojaRepository.findAll();
    }
    
    public Optional<Pooja> getPoojaById(Long id) {
        return poojaRepository.findById(id);
    }
    
    public Pooja createPooja(PoojaRequest request) {
        if (poojaRepository.existsByPoojaName(request.getPoojaName())) {
            throw new RuntimeException("Pooja with name " + request.getPoojaName() + " already exists");
        }
        
        Pooja pooja = new Pooja();
        pooja.setPoojaName(request.getPoojaName());
        pooja.setPoojaDetails(request.getPoojaDetails());
        pooja.setPoojaPrice(request.getPoojaPrice());
        
        return poojaRepository.save(pooja);
    }
    
    public Pooja updatePooja(Long id, PoojaRequest request) {
        Pooja pooja = poojaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pooja not found with id: " + id));
        
        if (!pooja.getPoojaName().equals(request.getPoojaName()) && 
            poojaRepository.existsByPoojaName(request.getPoojaName())) {
            throw new RuntimeException("Pooja with name " + request.getPoojaName() + " already exists");
        }
        
        pooja.setPoojaName(request.getPoojaName());
        pooja.setPoojaDetails(request.getPoojaDetails());
        pooja.setPoojaPrice(request.getPoojaPrice());
        
        return poojaRepository.save(pooja);
    }
    
    public void deletePooja(Long id) {
        if (!poojaRepository.existsById(id)) {
            throw new RuntimeException("Pooja not found with id: " + id);
        }
        poojaRepository.deleteById(id);
    }
}





