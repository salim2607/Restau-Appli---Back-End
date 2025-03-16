package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "allergene")
public class Allergene {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom; // Exemple : "Gluten", "Lait", "Fruits à coque", "Œufs"

    @ManyToMany(mappedBy = "allergenes")
    private List<Plat> plats;
}