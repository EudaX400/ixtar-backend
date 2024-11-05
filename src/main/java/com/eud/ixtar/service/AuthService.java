package com.eud.ixtar.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eud.ixtar.jwt.JwtUtils;
import com.eud.ixtar.users.User;
import com.eud.ixtar.users.UserRepository;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(User user) {
        
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "Error: El nombre de usuario ya existe";
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Error: El correo electrónico ya está registrado";
        }

        user.setPassword(passwordEncoder.encode((String) user.getPassword()));
        userRepository.save(user);
        return "Usuario registrado exitosamente";
    }

    public String login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
    
        if (user.isPresent() && passwordEncoder.matches(password, (String) user.get().getPassword())) {
            User foundUser = user.get();
            return JwtUtils.generateToken(foundUser);
        }
    
        throw new RuntimeException("Credenciales incorrectas");
    }

}
