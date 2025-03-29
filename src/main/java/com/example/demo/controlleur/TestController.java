package com.example.demo.controlleur;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*")
public class TestController {

    @GetMapping("/public")
    public String testPublic() {
        return "Public route OK ✅";
    }

    @GetMapping("/private")
    public String testPrivate() {
        return "Secured route ✅";
    }
}
