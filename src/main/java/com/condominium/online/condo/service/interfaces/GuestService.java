package com.condominium.online.condo.service.interfaces;

import com.condominium.online.condo.entity.GuestImpl;
import com.condominium.online.condo.exceptions.InvalidUserException;

public interface GuestService {

    GuestImpl saveGuest(GuestImpl guest) throws InvalidUserException;
}
