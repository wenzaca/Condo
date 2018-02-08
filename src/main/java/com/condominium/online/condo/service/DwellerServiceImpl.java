package com.condominium.online.condo.service;

import com.condominium.online.condo.entity.DwellerImpl;
import com.condominium.online.condo.exceptions.InvalidUserException;
import com.condominium.online.condo.repository.DwellerRepository;
import com.condominium.online.condo.service.interfaces.DwellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DwellerServiceImpl implements DwellerService {


    private DwellerRepository dwellerRepository;

    @Autowired
    public DwellerServiceImpl(DwellerRepository dwellerRepository){
        this.dwellerRepository = dwellerRepository;
    }

    @Override
    public DwellerImpl saveDweller(DwellerImpl dweller) throws InvalidUserException {

        Optional.of(dweller).map(DwellerImpl::getName).filter(name -> !name.isEmpty()).orElseThrow(() -> {
            return new InvalidUserException("Invalid Name");
        });

        Optional.of(dweller).map(DwellerImpl::getApartmentNumber).filter(apNumber -> !apNumber.isEmpty())
                .orElseThrow(() -> {
                    return new InvalidUserException("Invalid apartment");
                });

        Optional.of(dweller).map(DwellerImpl::getCpf).filter(cpf -> !cpf.isEmpty()).orElseThrow(() -> {
            return new InvalidUserException("Invalid CPF");
        });


        return dwellerRepository.save(dweller).orElseThrow(InvalidUserException::new);
    }

    @Override
    public void deleteDweller(long id) throws InvalidUserException {

        if(!dwellerRepository.exists(id)){
            throw new InvalidUserException("No such user");
        }

        dwellerRepository.delete(id);
    }

    public List<DwellerImpl> getAllDwellers(){
        return dwellerRepository.findAll();
    }
}
