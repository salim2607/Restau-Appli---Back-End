package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "boisson")
public class Boisson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private Double prix;
    private String imageUrl;

    @OneToMany(mappedBy = "boisson")
    private List<Commande> commandes;
}