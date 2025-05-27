package com.example.demo.controlleur;

import com.example.demo.entity.Commande;
import com.example.demo.repo.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuisine")
@CrossOrigin("*")
public class CuisinierController {

    @Autowired
    private CommandeRepository commandeRepo;

    @GetMapping("/a-cuisiner")
    public List<Commande> getCommandesACuisiner() {
        return commandeRepo.findByStatutPreparation("en attente");
    }

    @PutMapping("/changer-statut/{id}")
    public ResponseEntity<String> changerStatut(@PathVariable Long id, @RequestParam String statut) {
        Commande commande = commandeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
        commande.setStatutPaiement(statut);
        commandeRepo.save(commande);
        return ResponseEntity.ok("Statut mis à jour : " + statut);
    }
}