package com.condominium.online.condo.controller;

import com.condominium.online.condo.entity.Dweller;
import com.condominium.online.condo.exceptions.InvalidUserException;
import com.condominium.online.condo.service.DwellerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/condo/dweller")
@Api("v1 - Dweller")
public class DwellerController {

    private DwellerService dwellerService;

    @Autowired
    public DwellerController(DwellerService dwellerService) {
        this.dwellerService = dwellerService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create Dweller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCEPT", value = "ACCEPT", defaultValue = "application/json", required = false, dataType = "string", paramType = "header")})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "BAD_REQUEST"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")})
    public ResponseEntity<Dweller> saveDweller(@RequestBody(required = true) Dweller dweller) throws InvalidUserException {
        return new ResponseEntity(this.dwellerService.saveDweller(dweller), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Find all Dweller on database.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCEPT", value = "ACCEPT", defaultValue = "application/json", required = false, dataType = "string", paramType = "header")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", responseContainer = "List", response = Package.class),
            @ApiResponse(code = 400, message = "BAD_REQUEST"), @ApiResponse(code = 404, message = "NOT_FOUND"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")})
    public ResponseEntity<List<Dweller>> getDwellers() {
        return new ResponseEntity<>(this.dwellerService.getAllDwellers(), HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Data Integrity Violation")
    @ExceptionHandler({DataIntegrityViolationException.class})
    public void dataIntegrityViolation() {
    }
}
