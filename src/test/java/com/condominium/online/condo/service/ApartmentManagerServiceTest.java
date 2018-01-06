package com.condominium.online.condo.service;


import com.condominium.online.condo.entity.ApartmentManager;
import com.condominium.online.condo.exceptions.InvalidUserException;
import com.condominium.online.condo.repository.ApartmentManagerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class ApartmentManagerServiceTest {

    private static final String APARTMENT_MANAGER_1_NAME = "Godzilla";
    private static final String APARTMENT_MANAGER_1_CPF = "123.456.789.00";
    private static final String APARTMENT_MANAGER_1_APARTMENT_NUMBER = "68C";
    private ApartmentManager apartmentManager;


    @InjectMocks
    private ApartmentManagerService apartmentManagerService;


    @Mock
    private ApartmentManagerRepository apartmentManagerRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        apartmentManager = new ApartmentManager(APARTMENT_MANAGER_1_NAME, APARTMENT_MANAGER_1_CPF, APARTMENT_MANAGER_1_APARTMENT_NUMBER);
    }

    @Test
    public void whenSaveApartmentManagerThenReturnSavedDweller() throws Exception{
        when(apartmentManagerRepository.save(apartmentManager)).thenReturn(Optional.of(apartmentManager));

        ApartmentManager apartmentManager = apartmentManagerService.saveApartmentManager(this.apartmentManager);

        assertEquals(apartmentManager.getName(), APARTMENT_MANAGER_1_NAME);
        assertEquals(apartmentManager.getCpf(), APARTMENT_MANAGER_1_CPF);
        assertEquals(apartmentManager.getApartmentNumber(), APARTMENT_MANAGER_1_APARTMENT_NUMBER);
    }

    @Test(expected = InvalidUserException.class)
    public void whenSaveApartmentManagerWithNullNameThenReturnInvalidUserException() throws Exception{
        apartmentManager.setName(null);

        apartmentManagerService.saveApartmentManager(apartmentManager);
    }

    @Test(expected = InvalidUserException.class)
    public void whenSaveApartmentManagerWithNullCPFThenReturnInvalidUserException() throws Exception{
        apartmentManager.setCpf(null);

        apartmentManagerService.saveApartmentManager(apartmentManager);
    }

    @Test(expected = InvalidUserException.class)
    public void whenSavingDwellerWithNullApartmentThenReturnInvalidUserException() throws Exception{
        apartmentManager.setApartmentNumber(null);

        apartmentManagerService.saveApartmentManager(apartmentManager);
    }
}
