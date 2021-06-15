/*
package de.inmediasp.adressBook.service;

import de.inmediasp.adressBook.model.ContactEntity;
import de.inmediasp.adressBook.model.ContactDTO;
import de.inmediasp.adressBook.repository.ContactRepo;


import io.swagger.models.Contact;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class ContactEntityServiceImpTest_When_User_Information_Get_isCalled {
    @Mock
    private ContactRepo contactRepo;
    @Mock
    private ModelMapper modelMapper;

    private ContactServiceImp testable;
    private ContactEntity contactEntityTest = new ContactEntity("Ahmed", "Mesbah", "Berliner str1", "16540", "Germany", "ahmed@123");
    private ContactEntity contactEntityTest1 = new ContactEntity(7L, "Ahmed", "Mesbah", "Berliner str1", "16540", "Germany", "ahmed@123");

    @BeforeEach
    void init() {
        //contactRepo = mock(ContactRepo.class);
        MockitoAnnotations.openMocks(this);
        testable = new ContactServiceImp(contactRepo,modelMapper);
    }

    @Test
    void GIVEN_no_user_id_THEN_all_Users_are_returned() {

        doReturn(new ArrayList<>()).when(contactRepo).findAll();
        List result = testable.getAllContacts();
        Assert.assertEquals(0, result.size());
    }

    @Test
    void GIVEN_several_users_THEN_all_Users_are_returned() {
        List<ContactEntity> dbResult = new ArrayList<ContactEntity>();
        dbResult.add(contactEntityTest);
        doReturn(dbResult).when(contactRepo).findAll();
        List<ContactDTO> result = testable.getAllContacts();
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("Ahmed", result.get(0).getFName());
    }

    @Test
    void Test_Contact_Fields() {
        List<ContactEntity> dbResult = new ArrayList<ContactEntity>();
        dbResult.add(contactEntityTest);
        doReturn(dbResult).when(contactRepo).findAll();
        List<ContactDTO> result = testable.getAllContacts();
        assertEquals(1, result.size());
        assertEquals("ahmed@123", result.get(0).getEmail());
        assertEquals("Germany", result.get(0).getCountry());
        assertEquals("Ahmed", result.get(0).getFName());
        assertEquals("Berliner str1", result.get(0).getStreet());
        assertEquals("16540", result.get(0).getPostcode());
    }

    @Test
    void Give_id_Then_not_found_in_db() {
        List<ContactEntity> dbResult = new ArrayList<ContactEntity>();
        dbResult.add(contactEntityTest);
        assertNotNull(contactEntityTest);

    }


    @Test
    @Disabled
    void GIVEN_db_connection_timeout_THEN_return_empty_list() {

        doThrow(new Exception()).when(contactRepo).findAll();
        List<ContactDTO> result = testable.getAllContacts();
        Assert.assertEquals(0, result.size());

    }

    @Test
    void get_contact_using_id() {

        //  assertThrows(ApiRequestException.class, () -> testable.getContact(100L));
        given(contactRepo.findById(any())).willReturn(Optional.ofNullable(contactEntityTest1));
        var result = testable.getContact(1L);
        assertNotNull(result);
        assertEquals(contactEntityTest1.getfName(), result.getFName());
        assertEquals(contactEntityTest1.getlName(), result.getLName());
        assertEquals(contactEntityTest1.getCountry(), result.getCountry());
        assertEquals(contactEntityTest1.getStreet(), result.getStreet());
        assertEquals(contactEntityTest1.getPostcode(), result.getPostcode());
    }

    @Test
    void addContact() {
        ContactEntity con = new ContactEntity(
                "Ahmed",
                "Mesbah",
                "Berliner str1",
                "16540",
                "Germany",
                "ahmed@123");
        testable.addContact(con);
        ArgumentCaptor<ContactEntity> contactEntryArgumentCaptor = ArgumentCaptor.forClass(ContactEntity.class);
        verify(contactRepo).save(contactEntryArgumentCaptor.capture());
        ContactEntity captorContactEntity = contactEntryArgumentCaptor.getValue();
        assertThat(captorContactEntity.getfName()).isEqualTo(con.getfName());
        assertThat(captorContactEntity.getlName()).isEqualTo(con.getlName());
        assertThat(captorContactEntity.getStreet()).isEqualTo(con.getStreet());
        assertThat(captorContactEntity.getPostcode()).isEqualTo(con.getPostcode());
        assertThat(captorContactEntity.getEmail()).isEqualTo(con.getEmail());


    }

    @Test
    void update_contact_in_db() {
        ContactEntity con = new ContactEntity(
                "Ahmed",
                "Mesbah",
                "Berliner str1",
                "16540",
                "Germany",
                "ahmed@123");
        testable.updateContact(con);
        ArgumentCaptor<ContactEntity> contactEntryArgumentCaptor = ArgumentCaptor.forClass(ContactEntity.class);
        verify(contactRepo).save(contactEntryArgumentCaptor.capture());
        ContactEntity captorContactEntity = contactEntryArgumentCaptor.getValue();
        assertThat(captorContactEntity.getfName()).isEqualTo(con.getfName());
        assertThat(captorContactEntity.getlName()).isEqualTo(con.getlName());
        assertThat(captorContactEntity.getStreet()).isEqualTo(con.getStreet());
        assertThat(captorContactEntity.getPostcode()).isEqualTo(con.getPostcode());
        assertThat(captorContactEntity.getEmail()).isEqualTo(con.getEmail());


    }

    @Test
    public void testDeleteProduct() {
        given(contactRepo.findById(any())).willReturn(Optional.ofNullable(contactEntityTest1));
        var result = testable.getContact(7L);
        assertNotNull(result);
        assertNotEquals(null, testable.getContact(7L));


    }@ Test
    @Disabled
    void add_list_of_contacts_in_Db(){
        List<ContactEntity> contactList=new ArrayList<>();
        ContactEntity con = new ContactEntity(
                "Ahmed",
                "Mesbah",
                "Berliner str1",
                "16540",
                "Germany",
                "ahmed@123");
        contactList.add(con);
        testable.addListContacts(contactList);
        ArgumentCaptor<ContactEntity> contactEntryArgumentCaptor = ArgumentCaptor.forClass(ContactEntity.class);
        verify(contactRepo).save(contactEntryArgumentCaptor.capture());
        ContactEntity captorContactEntity = contactEntryArgumentCaptor.getValue();
        assertThat(captorContactEntity.getfName()).isEqualTo(contactList.get(0).getlName());

    }

}*/
