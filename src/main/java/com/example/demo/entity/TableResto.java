package com.example.demo.entity;

import com.example.demo.entity.Commande;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "table_resto")
@Data
public class TableResto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;         // T1, T2...
    private String emplacement;    // "salle" ou "terrasse"
    private int nombrePlaces;

    private String statut = "libre"; // libre | occupée | en attente

    @OneToMany(mappedBy = "tableResto")
    private List<Commande> commandes;
}