package com.example.demo.Service;

import com.example.demo.entity.Plat;
import com.example.demo.repo.PlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class PlatService {
    @Autowired
    private PlatRepository platRepository;
    private PlatService platService;

    public List<Plat> getAllPlats() {
        return platRepository.findAll();
    }

    public Plat savePlat(Plat plat) {
        return platRepository.save(plat);
    }

    public Plat editePlat(Plat plat, long id) {
        Optional<Plat> platOptional = platRepository.findById(id);

        if (platOptional.isPresent()) {
            Plat existingPlat = platOptional.get();
            existingPlat.setNom(plat.getNom());
            existingPlat.setDescription(plat.getDescription());
            existingPlat.setPrix(plat.getPrix());

            return platRepository.save(existingPlat); // Enregistre les modifications
        } else {
            throw new RuntimeException("Plat non trouvé avec l'ID : " + id);
        }
    }


    public Plat getPlatById(Long id) {
        return platRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plat non trouvé"));
    }
    public void deletePlat(Long id) {
        if (platRepository.existsById(id)) {
            platRepository.deleteById(id);
        } else {
            throw new RuntimeException("Plat non trouvé avec l'ID : " + id);
        }
    }
}
