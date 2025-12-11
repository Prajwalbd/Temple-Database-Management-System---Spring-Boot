package com.temple.temple_database.repository;

import com.temple.temple_database.model.Pooja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PoojaRepository extends JpaRepository<Pooja, Long> {
    Optional<Pooja> findByPoojaName(String poojaName);
    boolean existsByPoojaName(String poojaName);
}





