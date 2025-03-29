package com.example.demo.controlleur;

import com.example.demo.LoginRequest;
import com.example.demo.LoginResponse;
import com.example.demo.JwtUtils;
import com.example.demo.Service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

   /* @PostMapping("/login")
    public LoginResponse authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream().map(item -> item.getAuthority()).collect(Collectors.toList());

        return new LoginResponse(jwt, userDetails.getUsername(), roles);
    }*/
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

}
