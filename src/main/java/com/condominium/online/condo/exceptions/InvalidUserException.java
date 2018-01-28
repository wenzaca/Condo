package com.condominium.online.condo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid user data")
public class InvalidUserException extends Exception {

    public InvalidUserException(String message){
        super(message);
    }

    public InvalidUserException(){

    }
}
