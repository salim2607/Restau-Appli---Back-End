package com.example.demo.controllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommandeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateCommande() throws Exception {
        String json = """
        {
          "nomClient": "Jean Dupont",
          "statut": "en attente",
          "lignesCommande": [
            {
              "quantite": 1,
              "plat": { "id": 1 }
            }
          ]
        }
        """;

        mockMvc.perform(post("/api/commandes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }
}
