package com.temple.temple_database.repository;

import com.temple.temple_database.model.HallBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallBookingRepository extends JpaRepository<HallBooking, Long> {
}





