package com.condominium.online.condo.entity;

import com.condominium.online.condo.entity.interfaces.IGuest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Guest extends User implements IGuest {

    @Getter
    @Setter
    @NotNull
    private String guestFromApartment;

    public Guest(){

    }

    public Guest(String name, String cpf, String guestFromApartment) {
        super(name, cpf);
        this.guestFromApartment = guestFromApartment;
    }

}
