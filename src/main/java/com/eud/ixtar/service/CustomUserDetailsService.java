package com.eud.ixtar.service;

import com.eud.ixtar.users.User;
import com.eud.ixtar.users.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + email);
        }
    
        User foundUser = user.get();
        return org.springframework.security.core.userdetails.User.builder()
                .username(foundUser.getEmail()) 
                .password((String) foundUser.getPassword())
                .roles("USER") 
                .build();
    }
}
