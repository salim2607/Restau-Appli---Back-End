package com.example.demo.Service;

import com.example.demo.entity.Commande;
import com.example.demo.entity.LigneCommande;

import com.example.demo.repo.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {
    @Autowired
    private CommandeRepository commandeRepository;

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }


    public Commande saveCommande(Commande commande) {
        for (LigneCommande lc : commande.getLignesCommande()) {
            lc.setCommande(commande); // Important pour la relation bi-directionnelle
        }
        return commandeRepository.save(commande);
    }
}