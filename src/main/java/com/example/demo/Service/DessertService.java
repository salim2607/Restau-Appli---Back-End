package com.example.demo.Service;

import com.example.demo.entity.Dessert;
import com.example.demo.repo.DessertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DessertService {

    @Autowired
    private DessertRepository dessertRepository;

    public List<Dessert> getAllDesserts() {
        return dessertRepository.findAll();
    }

    public Dessert saveDessert(Dessert dessert) {
        return dessertRepository.save(dessert);
    }
}

