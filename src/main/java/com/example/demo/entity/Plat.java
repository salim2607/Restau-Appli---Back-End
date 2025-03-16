package com.example.demo.entity;

import com.example.demo.entity.Menu;
import jakarta.persistence.*;
import lombok.Data;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "plat")
public class Plat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private Double prix;
    private String imageUrl;

    @OneToMany(mappedBy = "plat")
    private List<Commande> commandes;
    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;
    @ManyToMany
    @JoinTable(
            name = "plat_allergene",
            joinColumns = @JoinColumn(name = "plat_id"),
            inverseJoinColumns = @JoinColumn(name = "allergene_id")
    )
    private List<Allergene> allergenes;
}

