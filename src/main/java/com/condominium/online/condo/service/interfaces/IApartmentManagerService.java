package com.condominium.online.condo.service.interfaces;

import com.condominium.online.condo.entity.ApartmentManager;
import com.condominium.online.condo.exceptions.InvalidUserException;

public interface  IApartmentManagerService {

    ApartmentManager saveApartmentManager(ApartmentManager ApartmentManager) throws InvalidUserException;

}
