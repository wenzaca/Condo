package com.condominium.online.condo.entity;

import com.condominium.online.condo.entity.interfaces.Guest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@EqualsAndHashCode
public class GuestImpl extends User implements Guest {

    @Getter
    @Setter
    @NotNull
    private String guestFromApartment;

    public GuestImpl(){

    }

    public GuestImpl(String name, String cpf, String guestFromApartment) {
        super(name, cpf);
        this.guestFromApartment = guestFromApartment;
    }

}
