package com.example.demo.repo;

import com.example.demo.entity.TableResto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TableRestoRepository extends JpaRepository<TableResto, Long> {
    Optional<TableResto> findByNumero(String numero);
    List<TableResto> findByEmplacement(String emplacement);
}
