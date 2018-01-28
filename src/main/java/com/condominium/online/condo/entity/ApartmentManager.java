package com.condominium.online.condo.entity;

import com.condominium.online.condo.entity.interfaces.IApartmentManager;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
public class ApartmentManager extends User implements IApartmentManager {

    // for jpa
    public ApartmentManager(){

    }

    public ApartmentManager(String name, String cpf, String apartmentNumber) {
        super(name, cpf, apartmentNumber);
    }
}
