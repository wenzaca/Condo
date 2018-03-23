package com.condominium.online.condo.service;

import com.condominium.online.condo.entity.GuestImpl;
import com.condominium.online.condo.exceptions.InvalidUserException;
import com.condominium.online.condo.repository.GuestRepository;
import com.condominium.online.condo.service.interfaces.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GuestServiceImpl implements GuestService {

    private GuestRepository guestRepository;

    @Autowired
    public GuestServiceImpl(GuestRepository guestRepository){
        this.guestRepository = guestRepository;
    }


    @Override
    public GuestImpl saveGuest(GuestImpl guest) throws InvalidUserException{
        Optional.of(guest).map(GuestImpl::getName).filter(name -> !name.isEmpty()).orElseThrow(() -> {
            return new InvalidUserException("Invalid Name");
        });

        Optional.of(guest).map(GuestImpl::getGuestFromApartment).filter(apNumber -> !apNumber.isEmpty())
                .orElseThrow(() -> {
                    return new InvalidUserException("Invalid apartment");
                });

        Optional.of(guest).map(GuestImpl::getCpf).filter(cpf -> !cpf.isEmpty()).orElseThrow(() -> {
            return new InvalidUserException("Invalid CPF");
        });


        return guestRepository.save(guest).orElseThrow(InvalidUserException::new);
    }
}
