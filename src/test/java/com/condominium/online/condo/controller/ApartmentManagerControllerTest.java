package com.condominium.online.condo.controller;

import com.condominium.online.condo.entity.ApartmentManager;
import com.condominium.online.condo.exceptions.InvalidUserException;
import com.condominium.online.condo.service.ApartmentManagerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApartmentManagerControllerTest {

    private static final String DWELLER_NAME = "John";
    private static final String DWELLER_CPF = "12345678998";
    private static final String DWELLER_APT = "3 pencil";

    private ApartmentManager apartmentManager;

    @Autowired
    private ObjectMapper jsonMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApartmentManagerService apartmentManagerService;

    @Before
    public void setUp(){
        this.apartmentManager = new ApartmentManager(DWELLER_NAME, DWELLER_CPF, DWELLER_APT);
    }

    @Test
    public void shouldReturn_ApartmentManager() throws Exception{

        when(apartmentManagerService.saveApartmentManager(apartmentManager)).thenReturn(new ApartmentManager(DWELLER_NAME, DWELLER_CPF, DWELLER_APT));

        this.mockMvc.perform(post("/apartmentManager").contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(apartmentManager)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(DWELLER_NAME))
                .andExpect(jsonPath("$.cpf").value(DWELLER_CPF))
                .andExpect(jsonPath("$.apartmentNumber").value(DWELLER_APT));
    }

    @Test
    public void emptyNameShouldReturn_BAD_REQUEST() throws Exception{
        apartmentManager.setName("");

        when(apartmentManagerService.saveApartmentManager(apartmentManager)).thenThrow(new InvalidUserException("Invalid name"));

        this.mockMvc.perform(post("/apartmentManager").contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(apartmentManager)))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Test
    public void emptyCPFShouldReturn_BAD_REQUEST() throws Exception{
        apartmentManager.setCpf("");

        when(apartmentManagerService.saveApartmentManager(apartmentManager)).thenThrow(new InvalidUserException("Invalid CPF"));

        this.mockMvc.perform(post("/apartmentManager").contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(apartmentManager)))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Test
    public void emptyApartmentShouldReturn_BAD_REQUEST() throws Exception{
        apartmentManager.setApartmentNumber("");

        when(apartmentManagerService.saveApartmentManager(apartmentManager)).thenThrow(new InvalidUserException("Invalid apartment"));

        this.mockMvc.perform(post("/apartmentManager").contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(apartmentManager)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void nullNameShouldReturn_BAD_REQUEST() throws Exception{
        apartmentManager.setName(null);

        when(apartmentManagerService.saveApartmentManager(apartmentManager)).thenThrow(new InvalidUserException("Invalid name"));

        this.mockMvc.perform(post("/apartmentManager").contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(apartmentManager)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void nullCPFShouldReturn_BAD_REQUEST() throws Exception{
        apartmentManager.setCpf(null);

        when(apartmentManagerService.saveApartmentManager(apartmentManager)).thenThrow(new InvalidUserException("Invalid CPF"));

        this.mockMvc.perform(post("/apartmentManager").contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(apartmentManager)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void nullApartmentShouldReturn_BAD_REQUEST() throws Exception{
        apartmentManager.setApartmentNumber(null);

        when(apartmentManagerService.saveApartmentManager(apartmentManager)).thenThrow(new InvalidUserException("Invalid apartment"));

        this.mockMvc.perform(post("/apartmentManager").contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(apartmentManager)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void emptyBodyShouldReturn_BAD_REQUEST() throws Exception{
        this.mockMvc.perform(post("/apartmentManager")).andExpect(status().isBadRequest());
    }
}
