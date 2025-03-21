package com.example.demo.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private String imageUrl; // URL de l'image

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    @JsonIgnore // ⚠️ Empêche la boucle infinie
    private List<Commande> commandes; // Un menu peut être commandé plusieurs fois
}