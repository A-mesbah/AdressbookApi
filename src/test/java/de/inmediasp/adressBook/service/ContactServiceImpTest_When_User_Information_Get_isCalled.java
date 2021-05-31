package de.inmediasp.adressBook.service;

import de.inmediasp.adressBook.exception.ApiException;
import de.inmediasp.adressBook.exception.ApiRequestException;
import de.inmediasp.adressBook.model.Contact;
import de.inmediasp.adressBook.model.ContactEntry;
import de.inmediasp.adressBook.repository.ContactRepo;


import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class ContactServiceImpTest_When_User_Information_Get_isCalled {
    @Mock
    private ContactRepo contactRepo;

    private ContactServiceImp testable;
    private Contact contactTest = new Contact("Ahmed", "Mesbah", "Berliner str1", "16540", "Germany", "ahmed@123");
    private Contact contactTest1 = new Contact(7L, "Ahmed", "Mesbah", "Berliner str1", "16540", "Germany", "ahmed@123");

    @BeforeEach
    void init() {
        //contactRepo = mock(ContactRepo.class);
        MockitoAnnotations.openMocks(this);
        testable = new ContactServiceImp(contactRepo);
    }

    @Test
    void GIVEN_no_user_id_THEN_all_Users_are_returned() {

        doReturn(new ArrayList<>()).when(contactRepo).findAll();
        List result = testable.getAllContacts();
        Assert.assertEquals(0, result.size());
    }

    @Test
    void GIVEN_several_users_THEN_all_Users_are_returned() {
        List<Contact> dbResult = new ArrayList<Contact>();
        dbResult.add(contactTest);
        doReturn(dbResult).when(contactRepo).findAll();
        List<ContactEntry> result = testable.getAllContacts();
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("Ahmed", result.get(0).getFName());
    }

    @Test
    void Test_Contact_Fields() {
        List<Contact> dbResult = new ArrayList<Contact>();
        dbResult.add(contactTest);
        doReturn(dbResult).when(contactRepo).findAll();
        List<ContactEntry> result = testable.getAllContacts();
        assertEquals(1, result.size());
        assertEquals("ahmed@123", result.get(0).getEmail());
        assertEquals("Germany", result.get(0).getCountry());
        assertEquals("Ahmed", result.get(0).getFName());
        assertEquals("Berliner str1", result.get(0).getStreet());
        assertEquals("16540", result.get(0).getPostcode());
    }

    @Test
    void Give_id_Then_not_found_in_db() {
        List<Contact> dbResult = new ArrayList<Contact>();
        dbResult.add(contactTest);
        assertNotNull(contactTest);

    }


    @Test
    @Disabled
    void GIVEN_db_connection_timeout_THEN_return_empty_list() {

        doThrow(new Exception()).when(contactRepo).findAll();
        List<ContactEntry> result = testable.getAllContacts();
        Assert.assertEquals(0, result.size());

    }

    @Test
    void get_contact_using_id() {

        //  assertThrows(ApiRequestException.class, () -> testable.getContact(100L));
        given(contactRepo.findById(any())).willReturn(Optional.ofNullable(contactTest1));
        var result = testable.getContact(1L);
        assertNotNull(result);
        assertEquals(contactTest1.getfName(), result.getFName());
        assertEquals(contactTest1.getlName(), result.getLName());
        assertEquals(contactTest1.getCountry(), result.getCountry());
        assertEquals(contactTest1.getStreet(), result.getStreet());
        assertEquals(contactTest1.getPostcode(), result.getPostcode());
    }

    @Test
    void addContact() {
        ContactEntry con = new ContactEntry(
                "Ahmed",
                "Mesbah",
                "Berliner str1",
                "16540",
                "Germany",
                "ahmed@123");
        testable.addContact(con);
        ArgumentCaptor<Contact> contactEntryArgumentCaptor = ArgumentCaptor.forClass(Contact.class);
        verify(contactRepo).save(contactEntryArgumentCaptor.capture());
        Contact captorContact = contactEntryArgumentCaptor.getValue();
        assertThat(captorContact.getfName()).isEqualTo(con.getFName());
        assertThat(captorContact.getlName()).isEqualTo(con.getLName());
        assertThat(captorContact.getStreet()).isEqualTo(con.getStreet());
        assertThat(captorContact.getPostcode()).isEqualTo(con.getPostcode());
        assertThat(captorContact.getEmail()).isEqualTo(con.getEmail());


    }

    @Test
    void update_contact_in_db() {
        ContactEntry con = new ContactEntry(
                "Ahmed",
                "Mesbah",
                "Berliner str1",
                "16540",
                "Germany",
                "ahmed@123");
        testable.updateContact(con);
        ArgumentCaptor<Contact> contactEntryArgumentCaptor = ArgumentCaptor.forClass(Contact.class);
        verify(contactRepo).save(contactEntryArgumentCaptor.capture());
        Contact captorContact = contactEntryArgumentCaptor.getValue();
        assertThat(captorContact.getfName()).isEqualTo(con.getFName());
        assertThat(captorContact.getlName()).isEqualTo(con.getLName());
        assertThat(captorContact.getStreet()).isEqualTo(con.getStreet());
        assertThat(captorContact.getPostcode()).isEqualTo(con.getPostcode());
        assertThat(captorContact.getEmail()).isEqualTo(con.getEmail());


    }

    @Test
    public void testDeleteProduct() {
        given(contactRepo.findById(any())).willReturn(Optional.ofNullable(contactTest1));
        var result = testable.getContact(7L);
        assertNotNull(result);
        assertNotEquals(null, testable.getContact(7L));


    }@ Test
    void add_list_of_contacts_in_Db(){
        List<ContactEntry> contactList=new ArrayList<>();
        ContactEntry con = new ContactEntry(
                "Ahmed",
                "Mesbah",
                "Berliner str1",
                "16540",
                "Germany",
                "ahmed@123");
        contactList.add(con);
        testable.addListContacts(contactList);
        ArgumentCaptor<Contact> contactEntryArgumentCaptor = ArgumentCaptor.forClass(Contact.class);
        verify(contactRepo).save(contactEntryArgumentCaptor.capture());
        Contact captorContact = contactEntryArgumentCaptor.getValue();
        assertThat(captorContact.getfName()).isEqualTo(contactList.get(0).getLName());

    }

}