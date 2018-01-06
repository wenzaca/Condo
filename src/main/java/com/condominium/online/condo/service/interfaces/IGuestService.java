package com.condominium.online.condo.service.interfaces;

import com.condominium.online.condo.entity.Guest;
import com.condominium.online.condo.exceptions.InvalidUserException;

public interface IGuestService {

    Guest saveGuest(Guest guest) throws InvalidUserException;
}
