package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore // ⚠️ Empêche la boucle infinie
    private List<Plat> plats;
}