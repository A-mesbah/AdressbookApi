package de.inmediasp.adressBook.controller;

import de.inmediasp.adressBook.model.ContactDTO;
import de.inmediasp.adressBook.model.ContactEntity;
import de.inmediasp.adressBook.service.ContactServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
/*

@WebMvcTest //if i have more than one Controller i should give it as a parameter
class ContactEntityControllerTest {
    ContactDTO contactDTO;
    ContactEntity contactEntity;
    @Autowired
    private MockMvc mockMvc;//to mock jason response and request
    @MockBean
    private ContactServiceImp contactServiceImp;

    @BeforeEach
    void setUp() {
        contactDTO = new ContactDTO("Max", "Mustermann", "Musterstr. 1", "12345", "Germany ", "max@mustermail.com");
        contactEntity = new ContactEntity("Max", "Mustermann", "Musterstr. 1", "12345", "Germany ", "max@mustermail.com");

    }

    @Test
    void get_ok_status_when_call_getAllContacts() throws Exception {
        mockMvc.perform(get("/api/v1/contacts")).andExpect(status().isOk());

    }

    @Test
    void should_Fetch_AllMembers_InDB() throws Exception {
        //given
        List<ContactDTO> data = new ArrayList<ContactDTO>();
        data.add(contactDTO);
        assertNotNull(contactDTO);
        //when
        given(contactServiceImp.getAllContacts()).willReturn(data);
        //then
        mockMvc.perform(get("/api/v1/contacts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].fName", equalTo(contactDTO.getFName())))
                .andExpect(jsonPath("$[0].lName", equalTo(contactDTO.getLName())))
                .andExpect(jsonPath("$[0].street", equalTo(contactDTO.getStreet())))
                .andExpect(jsonPath("$[0].postcode", equalTo(contactDTO.getPostcode())))
                .andExpect(jsonPath("$[0].country", equalTo(contactDTO.getCountry())))
                .andExpect(jsonPath("$[0].email", equalTo(contactDTO.getEmail())))
                .andReturn();


    }


    @Test
    void getContactWithId() throws Exception {
        given(contactServiceImp.getContact(anyLong())).willReturn(contactDTO);
        mockMvc.perform(get("/api/v1/contact/5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].fName", equalTo(contactDTO.getFName())))
                .andExpect(jsonPath("$[0].lName", equalTo(contactDTO.getLName())))
                .andExpect(jsonPath("$[0].street", equalTo(contactDTO.getStreet())))
                .andExpect(jsonPath("$[0].postcode", equalTo(contactDTO.getPostcode())))
                .andExpect(jsonPath("$[0].country", equalTo(contactDTO.getCountry())))
                .andExpect(jsonPath("$[0].email", equalTo(contactDTO.getEmail())));
    }

    @Test
    void addContact() throws Exception {

    }

    @Test
    void addContactsList() {
    }

    @Test
    void updateContact() {
    }

    @Test
    void deleteContact() throws Exception {

        mockMvc.perform(delete("/addressbook/1"))
                .andExpect(status().isNoContent());
    }
}



*/
