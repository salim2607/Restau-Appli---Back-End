package com.example.demo.Service;

import com.example.demo.entity.Boisson;
import com.example.demo.repository.BoissonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoissonService {
    @Autowired
    private BoissonRepository boissonRepository;

    public List<Boisson> getAllBoissons() {
        return boissonRepository.findAll();
    }
}