package com.condominium.online.condo.service;

import com.condominium.online.condo.entity.Dweller;
import com.condominium.online.condo.exceptions.InvalidUserException;
import com.condominium.online.condo.repository.DwellerRepository;
import com.condominium.online.condo.service.interfaces.IDwellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DwellerService implements IDwellerService {


    private DwellerRepository dwellerRepository;

    @Autowired
    public DwellerService(DwellerRepository dwellerRepository){
        this.dwellerRepository = dwellerRepository;
    }

    @Override
    public Dweller saveDweller(Dweller dweller) throws InvalidUserException {

        Optional.of(dweller).map(Dweller::getName).filter(name -> !name.isEmpty()).orElseThrow(() -> {
            return new InvalidUserException("Invalid Name");
        });

        Optional.of(dweller).map(Dweller::getApartmentNumber).filter(apNumber -> !apNumber.isEmpty())
                .orElseThrow(() -> {
                    return new InvalidUserException("Invalid apartment");
                });

        Optional.of(dweller).map(Dweller::getCpf).filter(cpf -> !cpf.isEmpty()).orElseThrow(() -> {
            return new InvalidUserException("Invalid CPF");
        });


        return dwellerRepository.save(dweller).orElseThrow(InvalidUserException::new);
    }

    public List<Dweller> getAllDwellers(){
        return dwellerRepository.findAll();
    }
}
