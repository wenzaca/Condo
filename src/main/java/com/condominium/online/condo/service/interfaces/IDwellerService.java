package com.condominium.online.condo.service.interfaces;

import com.condominium.online.condo.entity.Dweller;
import com.condominium.online.condo.exceptions.InvalidUserException;

import java.util.List;
import java.util.Optional;

public interface IDwellerService {

    Dweller saveDweller(Dweller dweller) throws InvalidUserException;

}
