package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String statutPreparation = "à cuisiner"; // "à cuisiner", "prêt", "archivé"

    private String statutPaiement;         // <--- "payé" ou "non payé"

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<LigneCommande> lignesCommande;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableResto tableResto;
}