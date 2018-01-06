package com.condominium.online.condo.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@EqualsAndHashCode
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    long id;

    @Getter
    @Setter
    @NotNull
    private String name;

    @Getter
    @Setter
    @NotNull
    @Column(unique=true)
    private String cpf;

    @Getter
    @Setter
    @Column(unique=true)
    private String apartmentNumber;

    // For Jpa
    public User(){

    }

    public User(String name, String cpf){
        this.name = name;
        this.cpf = cpf;
    }

    public User(String name, String cpf, String apartmentNumber){
        this.name = name;
        this.cpf = cpf;
        this.apartmentNumber = apartmentNumber;
    }
}
