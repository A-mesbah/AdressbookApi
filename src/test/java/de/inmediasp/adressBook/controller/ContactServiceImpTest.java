package de.inmediasp.adressBook.controller;

import de.inmediasp.adressBook.model.ContactDTO;
import de.inmediasp.adressBook.service.ContactService;
import de.inmediasp.adressBook.service.ContactServiceImp;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebMvcTest
class ContactEntityServiceImp_integration_Test {

    private static final String DROP_TABLE_CONTACTS = "H2DB\\drop.sql";
    private static final String CREATE_TABLE_CONTACTS = "H2DB\\create.sql";
    private JdbcTemplate jdbc;
    private MockMvc mockMvc;


    @Before
    public void before() throws SQLException {
        ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(),
                new ClassPathResource(CREATE_TABLE_CONTACTS));
    }

    @After
    public void after() throws SQLException {
        ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(),
                new ClassPathResource(DROP_TABLE_CONTACTS));
    }

    @Autowired
    ContactService contactService= Mockito.mock(ContactServiceImp.class);
    @Autowired
    ContactDTO contactDTO;

    @Test
    void getAllContacts() {
    }

    @Test
    void getContact() {
        contactDTO = new ContactDTO(
                "ahmed",
                "mohamed",
                "Berliner STR 1",
                "123",
                "germany",
                "ahmed@gmail.com");
        assertEquals(0, JdbcTestUtils.countRowsInTableWhere(jdbc,
                "Contacts",
                "name= ."
        ));
        //contactService.addContact(contactEntery);
    }

    @Test
    void addContact() {
    }

    @Test
    void updateContact() {
    }

    @Test
    void deleteContact() {
    }

    @Test
    void addListContacts() {

    }
}
