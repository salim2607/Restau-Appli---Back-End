package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String statut; // "en attente", "validée", etc.

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<LigneCommande> lignesCommande;
    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableResto tableResto;
}