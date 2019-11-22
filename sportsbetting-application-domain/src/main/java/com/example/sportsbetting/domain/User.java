package com.example.sportsbetting.domain;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //összes leszármazott megy egy entitásba
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private String password;

    public User(){

    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
