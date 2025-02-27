package com.example.demo.controlleur;

import com.example.demo.entity.Plat;
import com.example.demo.Service.PlatService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
