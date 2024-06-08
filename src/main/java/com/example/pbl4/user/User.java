package com.example.pbl4.user;

import com.example.pbl4.config.Rol;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(unique = true)
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public User(Long id, String username, String password, Rol rol) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public User() {
    }

    public User(String username, String password, Rol rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }
}
