package com.eud.ixtar.users;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import com.eud.ixtar.brainstorming.Brainstorming;

@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime created_at;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Brainstorming> brainstormings;

    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }

    public CharSequence getPassword() {
        return password;
    }

    public void setPassword(String encode) {
        this.password = encode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getCreatedAt() {
        return created_at;
    }

    public List<Brainstorming> getBrainstormings() {
        return brainstormings;
    }

    public void setBrainstormings(List<Brainstorming> brainstormings) {
        this.brainstormings = brainstormings;
    }
}
