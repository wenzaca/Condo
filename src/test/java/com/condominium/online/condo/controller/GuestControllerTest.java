package com.condominium.online.condo.controller;

import com.condominium.online.condo.entity.GuestImpl;
import com.condominium.online.condo.exceptions.InvalidUserException;
import com.condominium.online.condo.service.GuestServiceImpl;
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
public class GuestControllerTest {

    private static final String GUEST_NAME = "John";
    private static final String GUEST_CPF = "12345678998";
    private static final String GUEST_APT = "3 pencil";

    private GuestImpl guest;

    @Autowired
    private ObjectMapper jsonMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GuestServiceImpl guestService;

    @Before
    public void setUp(){
        this.guest = new GuestImpl(GUEST_NAME, GUEST_CPF, GUEST_APT);
    }

    @Test
    public void shouldReturn_Guest() throws Exception{

        when(guestService.saveGuest(guest)).thenReturn(new GuestImpl(GUEST_NAME, GUEST_CPF, GUEST_APT));

        this.mockMvc.perform(post("/condo/guest").contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(guest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(GUEST_NAME))
                .andExpect(jsonPath("$.cpf").value(GUEST_CPF))
                .andExpect(jsonPath("$.guestFromApartment").value(GUEST_APT));
    }

    @Test
    public void emptyNameShouldReturn_BAD_REQUEST() throws Exception{
        guest.setName("");

        when(guestService.saveGuest(guest)).thenThrow(new InvalidUserException("Invalid name"));

        this.mockMvc.perform(post("/condo/guest").contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(guest)))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Test
    public void emptyCPFShouldReturn_BAD_REQUEST() throws Exception{
        guest.setCpf("");

        when(guestService.saveGuest(guest)).thenThrow(new InvalidUserException("Invalid CPF"));

        this.mockMvc.perform(post("/condo/guest").contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(guest)))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Test
    public void emptyApartmentShouldReturn_BAD_REQUEST() throws Exception{
        guest.setApartmentNumber("");

        when(guestService.saveGuest(guest)).thenThrow(new InvalidUserException("Invalid apartment"));

        this.mockMvc.perform(post("/condo/guest").contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(guest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void nullNameShouldReturn_BAD_REQUEST() throws Exception{
        guest.setName(null);

        when(guestService.saveGuest(guest)).thenThrow(new InvalidUserException("Invalid name"));

        this.mockMvc.perform(post("/condo/guest").contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(guest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void nullCPFShouldReturn_BAD_REQUEST() throws Exception{
        guest.setCpf(null);

        when(guestService.saveGuest(guest)).thenThrow(new InvalidUserException("Invalid CPF"));

        this.mockMvc.perform(post("/condo/guest").contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(guest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void nullApartmentShouldReturn_BAD_REQUEST() throws Exception{
        guest.setApartmentNumber(null);

        when(guestService.saveGuest(guest)).thenThrow(new InvalidUserException("Invalid apartment"));

        this.mockMvc.perform(post("/condo/guest").contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(guest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void emptyBodyShouldReturn_BAD_REQUEST() throws Exception{
        this.mockMvc.perform(post("/condo/guest")).andExpect(status().isBadRequest());
    }
}
