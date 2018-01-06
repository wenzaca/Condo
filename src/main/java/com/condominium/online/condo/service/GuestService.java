package com.condominium.online.condo.service;

import com.condominium.online.condo.entity.Guest;
import com.condominium.online.condo.exceptions.InvalidUserException;
import com.condominium.online.condo.repository.GuestRepository;
import com.condominium.online.condo.service.interfaces.IGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GuestService implements IGuestService {

    private GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository){
        this.guestRepository = guestRepository;
    }


    @Override
    public Guest saveGuest(Guest guest) throws InvalidUserException{
        Optional.of(guest).map(Guest::getName).filter(name -> !name.isEmpty()).orElseThrow(() -> {
            return new InvalidUserException("Invalid Name");
        });

        Optional.of(guest).map(Guest::getGuestFromApartment).filter(apNumber -> !apNumber.isEmpty())
                .orElseThrow(() -> {
                    return new InvalidUserException("Invalid apartment");
                });

        Optional.of(guest).map(Guest::getCpf).filter(cpf -> !cpf.isEmpty()).orElseThrow(() -> {
            return new InvalidUserException("Invalid CPF");
        });


        return guestRepository.save(guest).orElseThrow(InvalidUserException::new);
    }
}
