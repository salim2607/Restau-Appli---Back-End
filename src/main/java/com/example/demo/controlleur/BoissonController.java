package com.example.demo.controlleur;

import com.example.demo.entity.Boisson;
import com.example.demo.Service.BoissonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boissons")
@CrossOrigin(origins = "*") // Permet aux requêtes front-end (Next.js) d'accéder à l'API
public class BoissonController {
    @Autowired
    private BoissonService boissonService;

    @GetMapping
    public List<Boisson> getAllBoissons() {
        return boissonService.getAllBoissons();
    }
}
