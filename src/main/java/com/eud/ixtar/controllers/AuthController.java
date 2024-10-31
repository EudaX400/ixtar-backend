package com.eud.ixtar.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eud.ixtar.jwt.JwtUtils;
import com.eud.ixtar.service.AuthService;
import com.eud.ixtar.users.User;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        System.out.println("Intentando registrar usuario: " + user.getUsername());
        String result = authService.register(user);
        return ResponseEntity.ok(Map.of("message", result));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = authService.login(request.getEmail(), request.getPassword());
        String token = JwtUtils.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}

class LoginRequest {
    private String email;
    private String password;
    // Getters y setters
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
}

class AuthResponse {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
