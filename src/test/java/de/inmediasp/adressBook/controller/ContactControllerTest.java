package de.inmediasp.adressBook.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static  org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class ContactControllerTest {
    @Autowired
    private MockMvc mockMvc;

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
}