package com.example.demo.controlleur;

import com.example.demo.entity.Plat;
import com.example.demo.Service.PlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plats")
@CrossOrigin(origins = "*")
public class PlatController {
    @Autowired
    private PlatService platService;

    @GetMapping
    public List<Plat> getAllPlats() {
        return platService.getAllPlats();
    }

    @PostMapping
    public Plat createPlat(@RequestBody Plat plat) {
        return platService.savePlat(plat);
    }
    @PostMapping("/plats")
    public ResponseEntity<Plat> addPlat(@RequestBody Plat plat) {
        Plat saved = platService.savePlat(plat);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    @PutMapping("/{id}")  // Remove the duplicate "plats" from path
    public ResponseEntity<Plat> updatePlat(@PathVariable Long id, @RequestBody Plat updatedPlat) {
        Plat existingPlat;
        try {
            existingPlat = platService.getPlatById(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        existingPlat.setNom(updatedPlat.getNom());
        existingPlat.setPrix(updatedPlat.getPrix());
        existingPlat.setDescription(updatedPlat.getDescription());
        existingPlat.setImageUrl(updatedPlat.getImageUrl());
        existingPlat.setCategorie(updatedPlat.getCategorie());

        Plat saved = platService.savePlat(existingPlat);
        return ResponseEntity.ok(saved);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlat(@PathVariable Long id) {
        try {
            platService.deletePlat(id);
            return ResponseEntity.ok("Plat supprimé avec succès");
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

}


