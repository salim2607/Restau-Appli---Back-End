package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;



import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="reservation",
        uniqueConstraints = @UniqueConstraint(columnNames = {"dateHeure", "numeroTable"})) // Empêche les doublons
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomClient;
    private String email;

    private LocalDateTime dateHeure;
    private Integer nombrePersonnes;
    private Integer numeroTable; // Une table ne peut pas être réservée deux fois à la même heure
}

