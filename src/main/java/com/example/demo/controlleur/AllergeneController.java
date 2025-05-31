package com.example.demo.controlleur;


import com.example.demo.entity.Allergene;
import com.example.demo.repo.AllergeneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allergenes")
@CrossOrigin(origins = "*")
public class AllergeneController {
    @Autowired
    private AllergeneRepository allergeneRepository;

    @GetMapping
    public List<Allergene> getAllAllergenes() {
        return allergeneRepository.findAll();
    }

    @PostMapping
    public Allergene createAllergene(@RequestBody Allergene allergene) {

        return allergeneRepository.save(allergene);
    }
}