package com.eud.ixtar.controllers;

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
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = authService.login(request.getUsername(), request.getPassword());
        String token = JwtUtils.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}

class LoginRequest {
    private String username;
    private String password;
    // Getters y setters
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
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
