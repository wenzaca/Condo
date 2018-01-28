package com.condominium.online.condo.service;

import com.condominium.online.condo.entity.Guest;
import com.condominium.online.condo.exceptions.InvalidUserException;
import com.condominium.online.condo.repository.GuestRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class GuestServiceTest {

    private static final String GUEST1_NAME = "Godzilla";
    private static final String GUEST1_CPF = "36724476925";
    private static final String GUEST1_IN_APARTMENT_NUMBER = "68C";
    private Guest guest;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @InjectMocks
    private GuestService registerGuestService;


    @Mock
    private GuestRepository guestRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        guest = new Guest(GUEST1_NAME, GUEST1_CPF, GUEST1_IN_APARTMENT_NUMBER);
    }

    @Test
    public void whenSaveDwellerThenReturnSavedDweller() throws Exception{
        when(guestRepository.save(guest)).thenReturn(Optional.ofNullable(guest));

        Guest savedGuest = registerGuestService.saveGuest(guest);

        assertEquals(savedGuest.getName(), GUEST1_NAME);
        assertEquals(savedGuest.getCpf(), GUEST1_CPF);
        assertEquals(savedGuest.getGuestFromApartment(), GUEST1_IN_APARTMENT_NUMBER);
    }

    @Test
    public void whenSaveDwellerWithNullNameThenReturnInvalidUserException() throws Exception{
        guest.setName(null);

        expectedException.expect(InvalidUserException.class);
        expectedException.expectMessage("Invalid Name");

        registerGuestService.saveGuest(guest);
    }

    @Test
    public void whenSaveDwellerWithEmptyNameThenReturnInvalidUserException() throws Exception{
        guest.setName("");

        expectedException.expect(InvalidUserException.class);
        expectedException.expectMessage("Invalid Name");

        registerGuestService.saveGuest(guest);
    }

    @Test
    public void whenSaveDwellerWithNullCPFThenReturnInvalidUserException() throws Exception{
        guest.setCpf(null);

        expectedException.expect(InvalidUserException.class);
        expectedException.expectMessage("Invalid CPF");

        registerGuestService.saveGuest(guest);
    }

    @Test
    public void whenSaveDwellerWithEmptyCPFThenReturnInvalidUserException() throws Exception{
        guest.setCpf("");

        expectedException.expect(InvalidUserException.class);
        expectedException.expectMessage("Invalid CPF");

        registerGuestService.saveGuest(guest);
    }

    @Test
    public void whenSavingDwellerWithNullApartmentThenReturnInvalidUserException() throws Exception{
        guest.setGuestFromApartment(null);

        expectedException.expect(InvalidUserException.class);
        expectedException.expectMessage("Invalid apartment");

        registerGuestService.saveGuest(guest);
    }

    @Test
    public void whenSavingDwellerWithEmptyApartmentThenReturnInvalidUserException() throws Exception{
        guest.setGuestFromApartment("");

        expectedException.expect(InvalidUserException.class);
        expectedException.expectMessage("Invalid apartment");

        registerGuestService.saveGuest(guest);
    }
}
