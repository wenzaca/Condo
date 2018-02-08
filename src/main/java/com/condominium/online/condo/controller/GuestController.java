package com.condominium.online.condo.controller;

import com.condominium.online.condo.entity.GuestImpl;
import com.condominium.online.condo.exceptions.InvalidUserException;
import com.condominium.online.condo.service.GuestServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/condo/guest")
@Api("v1 - GuestImpl")
public class GuestController {

    private GuestServiceImpl guestService;

    @Autowired
    public GuestController(GuestServiceImpl guestService) {
        this.guestService = guestService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create GuestImpl")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCEPT", value = "ACCEPT", defaultValue = "application/json", required = false, dataType = "string", paramType = "header")})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "BAD_REQUEST"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")})
    @ResponseStatus(code = HttpStatus.CREATED)
    public GuestImpl saveGuest(@RequestBody(required = true) GuestImpl guest) throws InvalidUserException {
        return this.guestService.saveGuest(guest);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Data Integrity Violation")
    @ExceptionHandler({DataIntegrityViolationException.class})
    public void DataIntegrityViolation() {
    }
}
