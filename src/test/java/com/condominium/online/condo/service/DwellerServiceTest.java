package com.condominium.online.condo.service;

import com.condominium.online.condo.entity.DwellerImpl;
import com.condominium.online.condo.exceptions.InvalidUserException;
import com.condominium.online.condo.repository.DwellerRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class DwellerServiceTest {

    private static final String DWELLER1_NAME = "Godzilla";
    private static final String DWELLER1_CPF = "36724476925";
    private static final String DWELLER1_APARTMENT_NUMBER = "68C";
    private DwellerImpl dweller1;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @InjectMocks
    private DwellerServiceImpl registerDwellerService;

    @Mock
    private DwellerRepository dwellerRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        dweller1 = new DwellerImpl(DWELLER1_NAME, DWELLER1_CPF, DWELLER1_APARTMENT_NUMBER);
    }

    @Test
    public void whenSaveDwellerThenReturnSavedDweller() throws Exception{
        when(dwellerRepository.save(dweller1)).thenReturn(Optional.ofNullable(dweller1));

        DwellerImpl savedDweller = registerDwellerService.saveDweller(dweller1);

        assertEquals(savedDweller.getName(), DWELLER1_NAME);
        assertEquals(savedDweller.getCpf(), DWELLER1_CPF);
        assertEquals(savedDweller.getApartmentNumber(), DWELLER1_APARTMENT_NUMBER);
    }

    @Test
    public void whenSaveDwellerWithNullNameThenReturnInvalidUserException() throws Exception{
        dweller1.setName(null);

        expectedException.expect(InvalidUserException.class);
        expectedException.expectMessage("Invalid Name");

        registerDwellerService.saveDweller(dweller1);
    }

    @Test
    public void whenSaveDwellerWithEmptyNameThenReturnInvalidUserException() throws Exception{
        dweller1.setName("");

        expectedException.expect(InvalidUserException.class);
        expectedException.expectMessage("Invalid Name");

        registerDwellerService.saveDweller(dweller1);
    }

    @Test
    public void whenSaveDwellerWithNullCPFThenReturnInvalidUserException() throws Exception{
        dweller1.setCpf(null);

        expectedException.expect(InvalidUserException.class);
        expectedException.expectMessage("Invalid CPF");

        registerDwellerService.saveDweller(dweller1);
    }

    @Test
    public void whenSaveDwellerWithEmptyCPFThenReturnInvalidUserException() throws Exception{
        dweller1.setCpf("");

        expectedException.expect(InvalidUserException.class);
        expectedException.expectMessage("Invalid CPF");

        registerDwellerService.saveDweller(dweller1);
    }

    @Test
    public void whenSavingDwellerWithNullApartmentThenReturnInvalidUserException() throws Exception{
        dweller1.setApartmentNumber(null);

        expectedException.expect(InvalidUserException.class);
        expectedException.expectMessage("Invalid apartment");

        registerDwellerService.saveDweller(dweller1);
    }

    @Test
    public void whenSavingDwellerWithEmptyApartmentThenReturnInvalidUserException() throws Exception{
        dweller1.setApartmentNumber("");

        expectedException.expect(InvalidUserException.class);
        expectedException.expectMessage("Invalid apartment");

        registerDwellerService.saveDweller(dweller1);
    }

    @Test
    public void whenRemovingExistingUserThenReturnSuccessful() throws InvalidUserException{
        dweller1.setId(123);

        when(dwellerRepository.exists(dweller1.getId())).thenReturn(true);
        doNothing().when(dwellerRepository).delete(dweller1.getId());

        registerDwellerService.deleteDweller(dweller1.getId());

        verify(dwellerRepository).delete(123);
    }

    @Test
    public void whenRemovingNonExistingUserIdThenReturnInvalidUserException() throws InvalidUserException{
        dweller1.setId(0);

        expectedException.expect(InvalidUserException.class);
        expectedException.expectMessage("No such user");

        when(dwellerRepository.exists(dweller1.getId())).thenReturn(false);
        doNothing().when(dwellerRepository).delete(dweller1.getId());

        registerDwellerService.deleteDweller(dweller1.getId());
    }
}
