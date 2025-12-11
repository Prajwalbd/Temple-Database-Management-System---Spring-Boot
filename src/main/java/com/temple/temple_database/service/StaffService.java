package com.temple.temple_database.service;

import com.temple.temple_database.dto.StaffRequest;
import com.temple.temple_database.model.Staff;
import com.temple.temple_database.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    
    @Autowired
    private StaffRepository staffRepository;
    
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }
    
    public Optional<Staff> getStaffById(Long id) {
        return staffRepository.findById(id);
    }
    
    public Staff createStaff(StaffRequest request) {
        Staff staff = new Staff();
        staff.setStaffName(request.getStaffName());
        staff.setStaffPhone(request.getStaffPhone());
        staff.setStaffDetails(request.getStaffDetails());
        staff.setStaffRole(request.getStaffRole());
        staff.setStaffSalary(request.getStaffSalary());
        
        return staffRepository.save(staff);
    }
    
    public Staff updateStaff(Long id, StaffRequest request) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + id));
        
        staff.setStaffName(request.getStaffName());
        staff.setStaffPhone(request.getStaffPhone());
        staff.setStaffDetails(request.getStaffDetails());
        staff.setStaffRole(request.getStaffRole());
        staff.setStaffSalary(request.getStaffSalary());
        
        return staffRepository.save(staff);
    }
    
    public void deleteStaff(Long id) {
        if (!staffRepository.existsById(id)) {
            throw new RuntimeException("Staff not found with id: " + id);
        }
        staffRepository.deleteById(id);
    }
}





