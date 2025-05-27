package com.example.demo.controlleur;

import com.example.demo.Service.CommandeService;
import com.example.demo.Service.TicketPdfService;
import com.example.demo.entity.Commande;
import com.example.demo.repo.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/paiement")
@CrossOrigin(origins = "*")
public class PaiementController {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private TicketPdfService ticketPdfService;

    @PostMapping("/carte")
    public ResponseEntity<Map<String, Object>> payerCommande(@RequestParam Long commandeId) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));

        commande.setStatutPaiement("payée");
        commandeRepository.save(commande);

        String ticketText = commandeService.genererTicket(commande);
        byte[] pdfBytes = ticketPdfService.generateTicketPdf(ticketText);

        String factureUrl = "/api/paiement/facture/" + commandeId;

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Paiement validé avec succès");
        response.put("commandeId", commandeId);
        response.put("statut", "payée");
        response.put("factureUrl", factureUrl);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/facture/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getFacture(@PathVariable Long id) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));

        String ticketText = commandeService.genererTicket(commande);
        byte[] pdfBytes = ticketPdfService.generateTicketPdf(ticketText);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=facture_" + id + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}

