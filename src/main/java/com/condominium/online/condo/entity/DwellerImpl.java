package com.condominium.online.condo.entity;

import com.condominium.online.condo.entity.interfaces.Dweller;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
@EqualsAndHashCode
public class DwellerImpl extends User implements Dweller {

    public DwellerImpl(){

    }

    public DwellerImpl(String name, String cpf, String apartmentNumber) {
        super(name, cpf, apartmentNumber);
    }
}