package com.condominium.online.condo.service;

import com.condominium.online.condo.entity.ApartmentManager;
import com.condominium.online.condo.exceptions.InvalidUserException;
import com.condominium.online.condo.repository.ApartmentManagerRepository;
import com.condominium.online.condo.service.interfaces.IApartmentManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApartmentManagerService implements IApartmentManagerService {

    ApartmentManagerRepository apartmentManagerRepository;

    @Autowired
    public ApartmentManagerService(ApartmentManagerRepository apartmentManagerRepository){
        this.apartmentManagerRepository = apartmentManagerRepository;
    }

    @Override
    public ApartmentManager saveApartmentManager(ApartmentManager apartmentManager) throws InvalidUserException {
        Optional.of(apartmentManager).map(ApartmentManager::getName).filter(name -> !name.isEmpty()).orElseThrow(() -> {
            return new InvalidUserException("Invalid Name");
        });

        Optional.of(apartmentManager).map(ApartmentManager::getApartmentNumber).filter(apNumber -> !apNumber.isEmpty())
                .orElseThrow(() -> {
                    return new InvalidUserException("Invalid apartment");
                });

        Optional.of(apartmentManager).map(ApartmentManager::getCpf).filter(cpf -> !cpf.isEmpty()).orElseThrow(() -> {
            return new InvalidUserException("Invalid CPF");
        });


        return apartmentManagerRepository.save(apartmentManager).orElseThrow(InvalidUserException::new);
    }
}
