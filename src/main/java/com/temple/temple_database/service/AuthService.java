package com.temple.temple_database.service;

import com.temple.temple_database.dto.AuthResponse;
import com.temple.temple_database.dto.LoginRequest;
import com.temple.temple_database.dto.RegisterRequest;
import com.temple.temple_database.model.User;
import com.temple.temple_database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByPhone(request.getPhone())) {
            return new AuthResponse("Phone number already registered", false);
        }
        
        User user = new User();
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        
        User savedUser = userRepository.save(user);
        return new AuthResponse("Registration successful", true, savedUser.getId(), savedUser.getName());
    }
    
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByPhone(request.getPhone())
                .orElse(null);
        
        if (user == null) {
            return new AuthResponse("Invalid phone or password", false);
        }
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new AuthResponse("Invalid phone or password", false);
        }
        
        return new AuthResponse("Login successful", true, user.getId(), user.getName());
    }
}




