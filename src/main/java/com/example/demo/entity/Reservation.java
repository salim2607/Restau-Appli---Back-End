package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomClient;
    private LocalDateTime dateHeure;
    private Integer nombrePersonnes;
    private String statut; // "confirmé", "annulé", "en attente"
    private String imageUrl; // Ajout du champ pour stocker l'URL de l'image

    // Getters et Setters

}
