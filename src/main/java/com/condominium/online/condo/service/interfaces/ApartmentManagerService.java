package com.condominium.online.condo.service.interfaces;

import com.condominium.online.condo.entity.ApartmentManagerImpl;
import com.condominium.online.condo.exceptions.InvalidUserException;

public interface ApartmentManagerService {

    ApartmentManagerImpl saveApartmentManager(ApartmentManagerImpl ApartmentManager) throws InvalidUserException;

    void deleteApartmentManager(long id) throws InvalidUserException;

}
