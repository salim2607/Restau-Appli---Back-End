package com.example.demo.repo;

import com.example.demo.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByDateHeureAndNumeroTable(LocalDateTime dateHeure, Integer numeroTable);
}

