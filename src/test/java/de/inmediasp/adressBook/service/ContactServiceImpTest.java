package de.inmediasp.adressBook.service;

import de.inmediasp.adressBook.model.ContactEntery;
import org.h2.tools.Script;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ContactServiceImpTest {
    private static final String DROP_TABLE_CONTACTS = "H2DB\\drop.sql";
    private static final String CREATE_TABLE_CONTACTS = "H2DB\\create.sql";
    private JdbcTemplate jdbc;


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
ContactService contactService;
    @Autowired
    ContactEntery contactEntery;
    @Test
    void getAllContacts() {
    }

    @Test
    void getContact() {
        contactEntery= new ContactEntery(
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