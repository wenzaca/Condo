package com.condominium.online.condo.entity;

import com.condominium.online.condo.entity.interfaces.ApartmentManager;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
@EqualsAndHashCode
public class ApartmentManagerImpl extends User implements ApartmentManager {

    // for jpa
    public ApartmentManagerImpl(){

    }

    public ApartmentManagerImpl(String name, String cpf, String apartmentNumber) {
        super(name, cpf, apartmentNumber);
    }
}
