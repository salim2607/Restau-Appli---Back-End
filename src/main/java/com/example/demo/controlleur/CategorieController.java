package com.example.demo.controlleur;

import com.example.demo.entity.Categorie;
import com.example.demo.repo.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategorieController {
    @Autowired
    private CategorieRepository categorieRepository;

    @GetMapping
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    @PostMapping
    public Categorie createCategorie(@RequestBody Categorie categorie) {

        return categorieRepository.save(categorie);
    }
}