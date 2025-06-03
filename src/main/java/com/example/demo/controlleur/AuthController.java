package com.example.demo.controlleur;

import com.example.demo.LoginRequest;
import com.example.demo.LoginResponse;
import com.example.demo.JwtUtils;
import com.example.demo.RegisterRequest;
import com.example.demo.Service.UserDetailsImpl;
import com.example.demo.entity.ERole;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repo.RoleRepository;
import com.example.demo.repo.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return "Erreur: Email déjà utilisé !";
        }

        // Créer un nouvel utilisateur
        User user = new User();
        user.setNom(registerRequest.getNom());
        user.setPrenom(registerRequest.getPrenom());
        user.setEmail(registerRequest.getEmail());
        user.setTelephone(registerRequest.getTelephone());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword())); // Encoder mot de passe 🔥

        Set<Role> roles = registerRequest.getRoles().stream().map(roleName -> {
            ERole eRole = ERole.valueOf(roleName); // Convertir "ROLE_ADMIN" ➔ ERole.ROLE_ADMIN
            return roleRepository.findByName(eRole)
                    .orElseThrow(() -> new RuntimeException("Erreur : rôle non trouvé."));
        }).collect(Collectors.toSet());

        user.setRoles(roles);
        userRepository.save(user);

        return "Utilisateur créé avec succès !";
    }


   @PostMapping("/login")
   public LoginResponse authenticateUser(@RequestBody LoginRequest loginRequest) {
       System.out.println("Tentative de connexion avec : " + loginRequest.getEmail());

       Authentication authentication = authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
       );

       SecurityContextHolder.getContext().setAuthentication(authentication);

       String jwt = jwtUtils.generateJwtToken(authentication);

       UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
       List<String> roles = userDetails.getAuthorities()
               .stream().map(item -> item.getAuthority()).collect(Collectors.toList());

       return new LoginResponse(jwt, userDetails.getUsername(), roles);
   }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return "Utilisateur introuvable.";
        }
        userRepository.deleteById(id);
        return "Utilisateur supprimé avec succès.";
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody RegisterRequest updateRequest) {
        try {
            // 1. Vérifier si l'utilisateur existe
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'ID: " + id));

            // 2. Vérifier si l'email est déjà utilisé par un autre utilisateur
            if (!user.getEmail().equals(updateRequest.getEmail()) &&
                    userRepository.existsByEmail(updateRequest.getEmail())) {
                return ResponseEntity
                        .badRequest()
                        .body("Erreur: Email déjà utilisé par un autre utilisateur!");
            }

            // 3. Mettre à jour les champs de base
            user.setNom(updateRequest.getNom());
            user.setPrenom(updateRequest.getPrenom());
            user.setEmail(updateRequest.getEmail());
            user.setTelephone(updateRequest.getTelephone());

            // 4. Mettre à jour le mot de passe seulement si fourni
            if (updateRequest.getPassword() != null && !updateRequest.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(updateRequest.getPassword()));
            }

            // 5. Mettre à jour les rôles
            if (updateRequest.getRoles() != null && !updateRequest.getRoles().isEmpty()) {
                Set<Role> roles = updateRequest.getRoles().stream()
                        .map(roleName -> {
                            ERole eRole = ERole.valueOf(roleName);
                            return roleRepository.findByName(eRole)
                                    .orElseThrow(() -> new RuntimeException("Erreur : rôle non trouvé."));
                        })
                        .collect(Collectors.toSet());
                user.setRoles(roles);
            }

            // 6. Sauvegarder les modifications
            userRepository.save(user);

            return ResponseEntity.ok("Utilisateur mis à jour avec succès!");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la mise à jour: " + e.getMessage());
        }
    }

}
