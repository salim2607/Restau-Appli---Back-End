package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "categorie")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom; // Exemple : "Pizzas", "Pâtes", "Salades", etc.

    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL)
    private List<Plat> plats;
}
