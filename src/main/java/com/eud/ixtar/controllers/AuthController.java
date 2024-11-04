package com.eud.ixtar.controllers;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eud.ixtar.jwt.JwtUtils;
import com.eud.ixtar.service.AuthService;
import com.eud.ixtar.users.User;
import com.eud.ixtar.users.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private UserRepository userRepository;

    public AuthController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
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

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Principal principal) {
        String email = principal.getName();
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Map<String, Object> userInfo = Map.of(
                    "email", user.getEmail(),
                    "username", user.getUsername(),
                    "created_at", user.getCreatedAt());
            return ResponseEntity.ok(userInfo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
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
