package com.example.demo.testService;

import com.example.demo.Service.CommandeService;
import com.example.demo.entity.Commande;
import com.example.demo.repo.CommandeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CommandeServiceTest {

    @Mock
    private CommandeRepository commandeRepository;

    @InjectMocks
    private CommandeService commandeService;

    public CommandeServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCommande() {
        Commande commande = new Commande();
        commande.setStatut("en attente"); // ✅ On teste un champ existant

        when(commandeRepository.save(any())).thenReturn(commande);

        Commande saved = commandeService.saveCommande(commande);
        assertEquals("en attente", saved.getStatut());
    }
}
