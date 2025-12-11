package com.temple.temple_database.repository;

import com.temple.temple_database.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {
    Optional<Hall> findByHallName(String hallName);
    boolean existsByHallName(String hallName);
}





