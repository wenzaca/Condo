package com.condominium.online.condo.controller;

import com.condominium.online.condo.entity.Guest;
import com.condominium.online.condo.exceptions.InvalidUserException;
import com.condominium.online.condo.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class GuestController {

    private GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService){
        this.guestService = guestService;
    }

    @PostMapping("/guest")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Guest saveGuest(@RequestBody Guest guest) throws InvalidUserException {
        return guestService.saveGuest(guest);
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Data Integrity Violation")
    @ExceptionHandler({DataIntegrityViolationException.class})
    public void DataIntegrityViolation(){
    }
}
