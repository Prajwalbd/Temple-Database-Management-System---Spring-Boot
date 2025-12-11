package com.temple.temple_database.controller;

import com.temple.temple_database.dto.PoojaRequest;
import com.temple.temple_database.model.Pooja;
import com.temple.temple_database.service.PoojaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/poojas")
@CrossOrigin(origins = "*")
public class PoojaController {
    
    @Autowired
    private PoojaService poojaService;
    
    @GetMapping
    public ResponseEntity<List<Pooja>> getAllPoojas() {
        return ResponseEntity.ok(poojaService.getAllPoojas());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Pooja> getPoojaById(@PathVariable Long id) {
        return poojaService.getPoojaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Pooja> createPooja(@Valid @RequestBody PoojaRequest request) {
        try {
            Pooja pooja = poojaService.createPooja(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(pooja);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Pooja> updatePooja(@PathVariable Long id, @Valid @RequestBody PoojaRequest request) {
        try {
            Pooja pooja = poojaService.updatePooja(id, request);
            return ResponseEntity.ok(pooja);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePooja(@PathVariable Long id) {
        try {
            poojaService.deletePooja(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}





