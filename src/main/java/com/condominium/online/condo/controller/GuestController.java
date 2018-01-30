package com.condominium.online.condo.controller;

import com.condominium.online.condo.entity.Guest;
import com.condominium.online.condo.exceptions.InvalidUserException;
import com.condominium.online.condo.service.GuestService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/condo/guest")
@Api("v1 - Guest")
public class GuestController {

    private GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create Guest")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCEPT", value = "ACCEPT", defaultValue = "application/json", required = false, dataType = "string", paramType = "header")})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "BAD_REQUEST"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")})
    public ResponseEntity<Guest> saveGuest(@RequestBody(required = true) Guest guest) throws InvalidUserException {
        return new ResponseEntity(this.guestService.saveGuest(guest), HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Data Integrity Violation")
    @ExceptionHandler({DataIntegrityViolationException.class})
    public void DataIntegrityViolation() {
    }
}
