package com.example.demo.controlleur;


import com.example.demo.entity.LigneCommande;

import com.example.demo.entity.Commande;
import com.example.demo.Service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
@CrossOrigin(origins = "*")
public class CommandeController {
    @Autowired
    private CommandeService commandeService;

    @GetMapping
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    @PostMapping
    public Commande createCommande(@RequestBody Commande commande) {
        for (LigneCommande lc : commande.getLignesCommande()) {
            lc.setCommande(commande); // Associer chaque ligne à la commande
        }
        return commandeService.saveCommande(commande);
    }
}
