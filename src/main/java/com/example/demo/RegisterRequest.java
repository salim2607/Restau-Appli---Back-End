package com.example.demo;

import lombok.Data;
import java.util.Set;

@Data
public class RegisterRequest {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String telephone;
    private Set<String> roles; // Exemples : ["ROLE_SERVEUR"], ["ROLE_CUISINIER"]
}