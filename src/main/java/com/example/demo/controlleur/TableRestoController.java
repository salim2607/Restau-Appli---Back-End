package com.example.demo.controlleur;

import com.example.demo.entity.Commande;
import com.example.demo.entity.TableResto;
import com.example.demo.repo.CommandeRepository;
import com.example.demo.repo.TableRestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
@CrossOrigin("*")
public class TableRestoController {

    @Autowired
    private TableRestoRepository tableRepo;

    @Autowired
    private CommandeRepository commandeRepo;

    @PutMapping("/affecter")
    public ResponseEntity<String> affecterCommandeATable(
            @RequestParam Long commandeId,
            @RequestParam String numeroTable) {

        TableResto table = tableRepo.findByNumero(numeroTable)
                .orElseThrow(() -> new RuntimeException("Table non trouvée"));
        Commande commande = commandeRepo.findById(commandeId)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));

        table.setStatut("occupée");
        commande.setTableResto(table);
        commande.setStatut("en attente");

        tableRepo.save(table);
        commandeRepo.save(commande);

        return ResponseEntity.ok("Commande affectée à la table " + numeroTable);
    }
    @PostMapping
    public ResponseEntity<TableResto> creerTable(@RequestBody TableResto table) {
        return ResponseEntity.ok(tableRepo.save(table));
    }
    @GetMapping
    public ResponseEntity<List<TableResto>> getAllTables() {
        return ResponseEntity.ok(tableRepo.findAll());
    }
}