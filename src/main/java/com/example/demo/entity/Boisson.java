package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="boisson")
public class Boisson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private Double prix;
    private String imageUrl; // Ajout du champ pour stocker l'URL de l'image

    // Getters et Setters
}
