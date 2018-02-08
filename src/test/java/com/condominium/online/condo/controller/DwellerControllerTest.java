package com.condominium.online.condo.controller;


import com.condominium.online.condo.entity.DwellerImpl;
import com.condominium.online.condo.exceptions.InvalidUserException;
import com.condominium.online.condo.service.DwellerServiceImpl;
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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DwellerControllerTest {

    private static final String DWELLER_NAME = "John";
    private static final String DWELLER_CPF = "12345678998";
    private static final String DWELLER_APT = "3 pencil";

    private DwellerImpl dweller;

    @Autowired
    private ObjectMapper jsonMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DwellerServiceImpl dwellerService;

    @Before
    public void setUp(){
        this.dweller = new DwellerImpl(DWELLER_NAME, DWELLER_CPF, DWELLER_APT);
    }

    @Test
    public void shouldReturn_Dweller() throws Exception{

        when(dwellerService.saveDweller(dweller)).thenReturn(new DwellerImpl(DWELLER_NAME, DWELLER_CPF, DWELLER_APT));

        this.mockMvc.perform(post("/condo/dweller").contentType(MediaType.APPLICATION_JSON)
            .content(jsonMapper.writeValueAsString(dweller)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(DWELLER_NAME))
                .andExpect(jsonPath("$.cpf").value(DWELLER_CPF))
                .andExpect(jsonPath("$.apartmentNumber").value(DWELLER_APT));
    }

    @Test
    public void emptyNameShouldReturn_BAD_REQUEST() throws Exception{
        dweller.setName("");

        when(dwellerService.saveDweller(dweller)).thenThrow(new InvalidUserException("Invalid name"));

        this.mockMvc.perform(post("/condo/dweller").contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(dweller)))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Test
    public void emptyCPFShouldReturn_BAD_REQUEST() throws Exception{
        dweller.setCpf("");

        when(dwellerService.saveDweller(dweller)).thenThrow(new InvalidUserException("Invalid CPF"));

        this.mockMvc.perform(post("/condo/dweller").contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(dweller)))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Test
    public void emptyApartmentShouldReturn_BAD_REQUEST() throws Exception{
        dweller.setApartmentNumber("");

        when(dwellerService.saveDweller(dweller)).thenThrow(new InvalidUserException("Invalid apartment"));

        this.mockMvc.perform(post("/condo/dweller").contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(dweller)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void nullNameShouldReturn_BAD_REQUEST() throws Exception{
        dweller.setName(null);

        when(dwellerService.saveDweller(dweller)).thenThrow(new InvalidUserException("Invalid name"));

        this.mockMvc.perform(post("/condo/dweller").contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(dweller)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void nullCPFShouldReturn_BAD_REQUEST() throws Exception{
        dweller.setCpf(null);

        when(dwellerService.saveDweller(dweller)).thenThrow(new InvalidUserException("Invalid CPF"));

        this.mockMvc.perform(post("/condo/dweller").contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(dweller)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void nullApartmentShouldReturn_BAD_REQUEST() throws Exception{
        dweller.setApartmentNumber(null);

        when(dwellerService.saveDweller(dweller)).thenThrow(new InvalidUserException("Invalid apartment"));

        this.mockMvc.perform(post("/condo/dweller").contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(dweller)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void emptyBodyShouldReturn_BAD_REQUEST() throws Exception{
        this.mockMvc.perform(post("/condo/dweller")).andExpect(status().isBadRequest());
    }

    @Test
    public void deleteDwellerShouldReturn_OK() throws Exception{
        doNothing().when(dwellerService).deleteDweller(dweller.getId());

        this.mockMvc.perform(delete("/condo/dweller/" + dweller.getId())).andExpect(status().isOk());
    }

    @Test
    public void deleteDwellerShouldReturn_ThrowInvalidUser() throws Exception{
        doThrow(new InvalidUserException("No such user")).when(dwellerService).deleteDweller(dweller.getId());

        this.mockMvc.perform(delete("/condo/dweller/" + dweller.getId())).andExpect(status().isBadRequest());
    }
}
