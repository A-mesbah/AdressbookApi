package de.inmediasp.adressBook.controller;

import de.inmediasp.adressBook.model.ContactEntry;
import de.inmediasp.adressBook.service.ContactServiceImp;
import de.inmediasp.adressBook.service.ContactWrite;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ContactControllerTest {
    /*
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ContactServiceImp contactServiceImp;
    private WebApplicationContext wac;
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void shouldFetchAllMembers() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(ContactController.URI)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(2)))
                .andReturn();
    }
    @Test
    void getAllContacts() throws Exception {
        mockMvc.perform(get("/api/v1/contacts")).andExpect(status().isOk());
    }


    @Test
    void getContactWithId() {
    }

    @Test
    void addContact() {
    }

    @Test
    void addContactsList() {
    }

    @Test
    void updateContact() {
    }

    @Test
    void deleteContact() {
    }

     */
}