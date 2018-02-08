package com.condominium.online.condo.service;

import com.condominium.online.condo.entity.ApartmentManagerImpl;
import com.condominium.online.condo.exceptions.InvalidUserException;
import com.condominium.online.condo.repository.ApartmentManagerRepository;
import com.condominium.online.condo.service.interfaces.ApartmentManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApartmentManagerServiceImpl implements ApartmentManagerService {

    ApartmentManagerRepository apartmentManagerRepository;

    @Autowired
    public ApartmentManagerServiceImpl(ApartmentManagerRepository apartmentManagerRepository){
        this.apartmentManagerRepository = apartmentManagerRepository;
    }

    @Override
    public ApartmentManagerImpl saveApartmentManager(ApartmentManagerImpl apartmentManager) throws InvalidUserException {
        Optional.of(apartmentManager).map(ApartmentManagerImpl::getName).filter(name -> !name.isEmpty()).orElseThrow(() -> {
            return new InvalidUserException("Invalid Name");
        });

        Optional.of(apartmentManager).map(ApartmentManagerImpl::getApartmentNumber).filter(apNumber -> !apNumber.isEmpty())
                .orElseThrow(() -> {
                    return new InvalidUserException("Invalid apartment");
                });

        Optional.of(apartmentManager).map(ApartmentManagerImpl::getCpf).filter(cpf -> !cpf.isEmpty()).orElseThrow(() -> {
            return new InvalidUserException("Invalid CPF");
        });


        return apartmentManagerRepository.save(apartmentManager).orElseThrow(InvalidUserException::new);
    }

    @Override
    public void deleteApartmentManager(long id) throws InvalidUserException {

        if(!apartmentManagerRepository.exists(id)){
            throw new InvalidUserException("No such apartment manager");
        }

        apartmentManagerRepository.delete(id);
    }


}
