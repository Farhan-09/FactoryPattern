package com.bxb.DemoCrud.user.Entity;

import jakarta.persistence.*;
import lombok.Getter;

import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @Column(unique = true, nullable = false)
    private String email;

//    @Column(nullable = false)
    private String password;

    // getters and setters
}
