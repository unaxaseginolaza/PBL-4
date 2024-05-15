package com.example.pbl4.user;

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
    private Integer permisos;

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

    public Integer getPermisos() {
        return permisos;
    }

    public void setPermisos(Integer permisos) {
        this.permisos = permisos;
    }

    public User(Long id, String username, String password, Integer permisos) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.permisos = permisos;
    }

    public User() {
    }

    public User(String username, String password, Integer permisos) {
        this.username = username;
        this.password = password;
        this.permisos = permisos;
    }
}
