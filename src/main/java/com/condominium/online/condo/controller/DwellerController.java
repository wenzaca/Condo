package com.condominium.online.condo.controller;

import com.condominium.online.condo.entity.Dweller;
import com.condominium.online.condo.exceptions.InvalidUserException;
import com.condominium.online.condo.service.DwellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DwellerController {

    private DwellerService dwellerService;

    @Autowired
    public DwellerController(DwellerService dwellerService){
        this.dwellerService = dwellerService;
    }

    @PostMapping("/dweller")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Dweller saveDweller(@RequestBody Dweller dweller) throws InvalidUserException {
        return dwellerService.saveDweller(dweller);
    }

    @GetMapping("/dwellers")
    public List<Dweller> getDwellers(){
        return dwellerService.getAllDwellers();
    }

    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Data Integrity Violation")
    @ExceptionHandler({DataIntegrityViolationException.class})
    public void dataIntegrityViolation(){
    }
}
