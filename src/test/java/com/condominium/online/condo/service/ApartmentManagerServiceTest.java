package com.condominium.online.condo.service;


import com.condominium.online.condo.entity.ApartmentManagerImpl;
import com.condominium.online.condo.exceptions.InvalidUserException;
import com.condominium.online.condo.repository.ApartmentManagerRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class ApartmentManagerServiceTest {

    private static final String APARTMENT_MANAGER_1_NAME = "Godzilla";
    private static final String APARTMENT_MANAGER_1_CPF = "123.456.789.00";
    private static final String APARTMENT_MANAGER_1_APARTMENT_NUMBER = "68C";
    private ApartmentManagerImpl apartmentManager;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @InjectMocks
    private ApartmentManagerServiceImpl apartmentManagerService;


    @Mock
    private ApartmentManagerRepository apartmentManagerRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        apartmentManager = new ApartmentManagerImpl(APARTMENT_MANAGER_1_NAME, APARTMENT_MANAGER_1_CPF, APARTMENT_MANAGER_1_APARTMENT_NUMBER);
    }

    @Test
    public void whenSaveApartmentManagerThenReturnSavedDweller() throws Exception{
        when(apartmentManagerRepository.save(apartmentManager)).thenReturn(Optional.of(apartmentManager));

        ApartmentManagerImpl apartmentManager = apartmentManagerService.saveApartmentManager(this.apartmentManager);

        assertEquals(apartmentManager.getName(), APARTMENT_MANAGER_1_NAME);
        assertEquals(apartmentManager.getCpf(), APARTMENT_MANAGER_1_CPF);
        assertEquals(apartmentManager.getApartmentNumber(), APARTMENT_MANAGER_1_APARTMENT_NUMBER);
    }

    @Test(expected = InvalidUserException.class)
    public void whenSaveApartmentManagerWithNullNameThenReturnInvalidUserException() throws Exception{
        apartmentManager.setName(null);

        expectedException.expect(InvalidUserException.class);
        expectedException.expectMessage("Invalid name");

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

    @Test
    public void whenRemovingExistingApartmentManagerThenReturnSuccessful() throws InvalidUserException{
        apartmentManager.setId(123);

        when(apartmentManagerRepository.exists(apartmentManager.getId())).thenReturn(true);
        doNothing().when(apartmentManagerRepository).delete(apartmentManager.getId());

        apartmentManagerService.deleteApartmentManager(apartmentManager.getId());

        verify(apartmentManagerRepository).delete(123);
    }

    @Test
    public void whenRemovingNonExistingApartmentManagerThenReturnInvalidUserException() throws InvalidUserException{
        apartmentManager.setId(-1);

        expectedException.expect(InvalidUserException.class);
        expectedException.expectMessage("No such apartment manager");

        when(apartmentManagerRepository.exists(apartmentManager.getId())).thenReturn(false);

        apartmentManagerService.deleteApartmentManager(apartmentManager.getId());
    }
}