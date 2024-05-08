package com.example.pbl4.users;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    private String password;

    public Users(Long id, String name, String surname, String password) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.fecha = fecha;
    }

    public Users() {
    }

    public Users(String name, float price, LocalDate fecha) {
        this.name = name;
        this.price = price;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", fecha=" + fecha +
                ", antiguedad=" + antiguedad +
                '}';
    }

}
