package com.example.springboot.restservice.restApiExample.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String rol;

    private User(){}

    public User(String name, String rol) {
        this.name = name;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRol() {
        return rol;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
