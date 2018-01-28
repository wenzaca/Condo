package com.condominium.online.condo.controller;

import com.condominium.online.condo.entity.ApartmentManager;
import com.condominium.online.condo.exceptions.InvalidUserException;
import com.condominium.online.condo.service.ApartmentManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApartmentManagerController {

    private ApartmentManagerService apartmentManagerService;

    @Autowired
    public ApartmentManagerController(ApartmentManagerService apartmentManagerService){
        this.apartmentManagerService = apartmentManagerService;
    }

    @PostMapping("/apartmentManager")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ApartmentManager saveApartmentManager(@RequestBody ApartmentManager apartmentManager) throws InvalidUserException {
        return apartmentManagerService.saveApartmentManager(apartmentManager);
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Data Integrity Violation")
    @ExceptionHandler({DataIntegrityViolationException.class})
    public void DataIntegrityViolation(){
    }

}
