package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class LigneCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantite;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "plat_id")
    private Plat plat;

    @ManyToOne
    @JoinColumn(name = "boisson_id")
    private Boisson boisson;

    @ManyToOne
    @JoinColumn(name = "dessert_id")
    private Dessert dessert;
}