package com.condominium.online.condo.service.interfaces;

import com.condominium.online.condo.entity.DwellerImpl;
import com.condominium.online.condo.exceptions.InvalidUserException;

public interface DwellerService {

    DwellerImpl saveDweller(DwellerImpl dweller) throws InvalidUserException;

    void deleteDweller(long id) throws InvalidUserException;

}
