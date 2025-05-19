package com.example.demo.Service;

import com.example.demo.entity.Commande;
import com.example.demo.entity.LigneCommande;

import com.example.demo.repo.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommandeService {
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private TicketPdfService ticketPdfService;
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }


    public Commande saveCommande(Commande commande) {
        for (LigneCommande lc : commande.getLignesCommande()) {
            lc.setCommande(commande); // Important pour la relation bi-directionnelle
        }
        return commandeRepository.save(commande);
    }
    public String genererTicket(Commande commande) {
        StringBuilder sb = new StringBuilder();
        sb.append("Ticket - Commande #").append(commande.getId()).append("\n");
        sb.append("Date : ").append(LocalDateTime.now()).append("\n\n");
        sb.append("--- Articles commandés ---\n");

        double totalHT = 0;

        for (LigneCommande lc : commande.getLignesCommande()) {
            String nom = "Inconnu";
            double prix = 0;

            if (lc.getPlat() != null && lc.getPlat().getNom() != null) {
                nom = lc.getPlat().getNom();
                prix = lc.getPlat().getPrix() != null ? lc.getPlat().getPrix() : 0;
            } else if (lc.getBoisson() != null) {
                nom = lc.getBoisson().getNom();
                prix = lc.getBoisson().getPrix() != null ? lc.getBoisson().getPrix() : 0;
            } else if (lc.getDessert() != null) {
                nom = lc.getDessert().getNom();
                prix = lc.getDessert().getPrix() != null ? lc.getDessert().getPrix() : 0;
            } else if (lc.getMenu() != null) {
                nom = lc.getMenu().getNom();
                prix = lc.getMenu().getPrix() != null ? lc.getMenu().getPrix() : 0;
            }

            double totalLigne = prix * lc.getQuantite();
            totalHT += totalLigne;

            sb.append(nom)
                    .append(" - ").append(lc.getQuantite()).append(" x ")
                    .append(prix).append("€ = ").append(totalLigne).append("€\n");
        }

        double tva = totalHT * 0.2;
        double totalTTC = totalHT + tva;

        sb.append("\nTotal HT : ").append(totalHT).append(" €\n");
        sb.append("TVA (20%) : ").append(tva).append(" €\n");
        sb.append("Total TTC : ").append(totalTTC).append(" €\n");

        return sb.toString();
    }

}