package com.example.demo.controlleur;

import com.example.demo.entity.Dessert;
import com.example.demo.Service.DessertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/desserts")
@CrossOrigin(origins = "*")
public class DessertController {

    @Autowired
    private DessertService dessertService;

    @GetMapping
    public List<Dessert> getAllDesserts() {
        return dessertService.getAllDesserts();
    }

    @PostMapping
    public Dessert createDessert(@RequestBody Dessert dessert) {
        return dessertService.saveDessert(dessert);
    }
}
