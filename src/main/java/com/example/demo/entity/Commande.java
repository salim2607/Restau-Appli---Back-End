package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "commande")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true)
    @JoinColumn(name = "menu_id", nullable = true)
    private Menu menu;

    @ManyToOne(optional = true)
    @JoinColumn(name = "plat_id", nullable = true)
    private Plat plat;

    @ManyToOne(optional = true)
    @JoinColumn(name = "boisson_id", nullable = true)
    private Boisson boisson;

    @ManyToOne(optional = true)
    @JoinColumn(name = "dessert_id", nullable = true)
    private Dessert dessert;
}
