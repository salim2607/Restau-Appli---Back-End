package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<Plat> plats;
    private String imageUrl; // Ajout du champ pour stocker l'URL de l'image

    // Getters et Setters
}
