package com.example.demo.repository;

import com.example.demo.entity.Boisson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoissonRepository extends JpaRepository<Boisson, Long> {
}
