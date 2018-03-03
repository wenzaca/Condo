package com.condominium.online.condo.controller;

import com.condominium.online.condo.entity.Dweller;
import com.condominium.online.condo.exceptions.InvalidUserException;
import com.condominium.online.condo.service.DwellerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(code = HttpStatus.CREATED)
    public Dweller saveDweller(@RequestBody(required = true) Dweller dweller) throws InvalidUserException {
        return this.dwellerService.saveDweller(dweller);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Find all Dweller on database.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCEPT", value = "ACCEPT", defaultValue = "application/json", required = false, dataType = "string", paramType = "header")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", responseContainer = "List", response = Package.class),
            @ApiResponse(code = 400, message = "BAD_REQUEST"), @ApiResponse(code = 404, message = "NOT_FOUND"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")})
    public List<Dweller> getDwellers() {
        return this.dwellerService.getAllDwellers();
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    @ApiOperation(value = "Update a dweller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCEPT", value = "ACCEPT", defaultValue = "application/json", required = false, dataType = "string", paramType = "header")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", responseContainer = "List", response = Package.class),
            @ApiResponse(code = 400, message = "BAD_REQUEST"), @ApiResponse(code = 404, message = "NOT_FOUND"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")})
    public Dweller updateDweller(@PathVariable("id") long id,
            @RequestBody(required = true) Dweller dweller) throws InvalidUserException {
        return dwellerService.updateDweller(id, dweller);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Dweller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCEPT", value = "ACCEPT", defaultValue = "application/json", required = false, dataType = "string", paramType = "header")})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "BAD_REQUEST"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")})
    public void deleteDweller(@PathVariable("id") long id) throws InvalidUserException{
        dwellerService.deleteDweller(id);
    }

    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Data Integrity Violation")
    @ExceptionHandler({DataIntegrityViolationException.class})
    public void dataIntegrityViolation() {
    }
}
