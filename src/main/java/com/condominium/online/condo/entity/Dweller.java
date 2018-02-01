package com.condominium.online.condo.entity;

import com.condominium.online.condo.entity.interfaces.IDweller;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
@EqualsAndHashCode
public class Dweller extends User implements IDweller {

    public Dweller(){

    }

    public Dweller(String name, String cpf, String apartmentNumber) {
        super(name, cpf, apartmentNumber);
    }
}