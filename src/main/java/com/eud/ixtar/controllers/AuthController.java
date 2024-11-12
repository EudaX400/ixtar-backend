package com.eud.ixtar.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eud.ixtar.project.Project;
import com.eud.ixtar.project.ProjectRepository;
import com.eud.ixtar.service.AuthService;
import com.eud.ixtar.users.User;
import com.eud.ixtar.users.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public AuthController(AuthService authService, UserRepository userRepository, ProjectRepository projectRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        System.out.println("Intentando registrar usuario: " + user.getUsername());
        String result = authService.register(user);
        return ResponseEntity.ok(Map.of("message", result));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied: Unauthorized User");
        }
        String email = principal.getName();
        Optional<User> userOptional = userRepository.findByEmail(email);
    
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            List<Project> projects = projectRepository.findByOwnerId(user.getId());

            List<Map<String, Object>> projectDetails = projects.stream().map(project -> {
                Map<String, Object> projectInfo = new HashMap<>();
                projectInfo.put("id", project.getId());
                projectInfo.put("name", project.getName());
                projectInfo.put("description", project.getDescription());
                projectInfo.put("type", project.getType());
                projectInfo.put("company", project.getCompany());
                projectInfo.put("created_at", project.getCreatedAt());
                projectInfo.put("finish_at", project.getFinishAt());
                return projectInfo;
            }).collect(Collectors.toList());

            Map<String, Object> userInfo = Map.of(
                    "email", user.getEmail(),
                    "username", user.getUsername(),
                    "created_at", user.getCreatedAt(),
                    "projects", projectDetails);

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
