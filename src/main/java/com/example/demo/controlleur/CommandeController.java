package com.example.demo.controlleur;


import com.example.demo.Service.*;
import com.example.demo.entity.Dessert;
import com.example.demo.entity.LigneCommande;

import com.example.demo.entity.Commande;
import com.example.demo.Service.CommandeService;

import com.example.demo.entity.Plat;
import com.example.demo.repo.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/commandes")
@CrossOrigin(origins = "*")
public class CommandeController {
    @Autowired
    private CommandeService commandeService;
    @Autowired
    private TicketPdfService ticketPdfService;
    @GetMapping
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    @PostMapping
    public Commande createCommande(@RequestBody Commande commande) {
        return commandeService.saveCommande(commande);
    }

    @Autowired
    private PlatService platService;

    @Autowired
    private BoissonService boissonService;

    @Autowired
    private DessertService dessertService;

    @Autowired
    private MenuService menuService;
    @Autowired
    private CommandeRepository commandeRepo;
    @PostMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> createCommandePdf(@RequestBody Commande commande) {
        for (LigneCommande lc : commande.getLignesCommande()) {
            lc.setCommande(commande);

            // Chargement explicite depuis la base
            if (lc.getPlat() != null && lc.getPlat().getId() != null) {
                lc.setPlat(platService.getPlatById(lc.getPlat().getId()));
            } else if (lc.getBoisson() != null && lc.getBoisson().getId() != null) {
                lc.setBoisson(boissonService.getBoissonById(lc.getBoisson().getId()));
            } else if (lc.getDessert() != null && lc.getDessert().getId() != null) {
                lc.setDessert(dessertService.getDessertById(lc.getDessert().getId()));
            } else if (lc.getMenu() != null && lc.getMenu().getId() != null) {
                lc.setMenu(menuService.getMenuById(lc.getMenu().getId()));
            }
        }

        Commande saved = commandeService.saveCommande(commande);
        String ticketText = commandeService.genererTicket(saved);
        byte[] pdfBytes = ticketPdfService.generateTicketPdf(ticketText);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=ticket_" + saved.getId() + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommande(@PathVariable Long id) {
        return commandeRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}/statutPaiement")
    public ResponseEntity<Commande> updateStatutCommande(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        return commandeRepo.findById(id)
                .map(commande -> {
                    commande.setStatutPaiement(body.get("statut"));
                    commandeRepo.save(commande);
                    return ResponseEntity.ok(commande);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}/preparation")
    public ResponseEntity<Commande> updateStatutPreparation(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        return commandeRepo.findById(id)
                .map(commande -> {
                    commande.setStatutPreparation(body.get("statutPreparation"));
                    commandeRepo.save(commande);
                    return ResponseEntity.ok(commande);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/preparation")
    public ResponseEntity<List<Commande>> getCommandesByStatutPreparation(@RequestParam String statutPreparation) {
        List<Commande> commandes = commandeRepo.findByStatutPreparation(statutPreparation);
        return ResponseEntity.ok(commandes);
    }
    @DeleteMapping("/commandes/{id}")
    public ResponseEntity<String> deleteCommande(@PathVariable Long id) {
        if (!commandeRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        commandeRepo.deleteById(id);
        return ResponseEntity.ok("Commande supprimée avec succès.");
    }
    @PutMapping("/commandes/{id}")
    public ResponseEntity<Commande> updateCommande(@PathVariable Long id, @RequestBody Commande updatedCommande) {
        return commandeRepo.findById(id).map(commande -> {
            commande.setStatutPreparation(updatedCommande.getStatutPreparation());
            commande.setStatutPaiement(updatedCommande.getStatutPaiement());
            commande.setTableResto(updatedCommande.getTableResto());
            commande.setLignesCommande(updatedCommande.getLignesCommande());
            updatedCommande.getLignesCommande().forEach(lc -> lc.setCommande(commande));
            commandeRepo.save(commande);
            return ResponseEntity.ok(commande);
        }).orElse(ResponseEntity.notFound().build());
    }

}
