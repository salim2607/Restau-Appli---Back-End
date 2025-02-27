package com.example.demo.entity;

import com.example.demo.entity.Menu;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="plat")
public class Plat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private Double prix;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    // Getters et Setters
}
