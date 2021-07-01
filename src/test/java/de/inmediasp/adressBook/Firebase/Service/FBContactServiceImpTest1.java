package de.inmediasp.adressBook.Firebase.Service;

import de.inmediasp.adressBook.model.ContactDTO;
import de.inmediasp.adressBook.model.ContactEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FBContactServiceImpTest1 {

    private FBContactServiceImp testable;
    private ContactDTO contact;
    @Mock
    private FBContactRepoImp contactRepo;
    @Mock
    private ModelMapper modelMaper;


    @BeforeEach
    void setup() {

        MockitoAnnotations.openMocks(this);
        testable = new FBContactServiceImp(contactRepo);
        contact = new ContactDTO("hallo",
                "hallo",
                "hallo",
                "hallo",
                "hallo",
                "hallo");


    }

    @Test
    void getAllContacts() {
        List<ContactDTO> mockedList = new ArrayList<>();
        mockedList.add(contact);
        //given
        given(contactRepo.findAllContacts()).willReturn(mockedList);
        //then
        List<ContactDTO> result = testable.getAllContacts();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mockedList.size(), result.size());
        assertEquals(mockedList, result);

    }

    @Test
    void getContactById() {
        //given
        ContactDTO contactDTO1 = new ContactDTO("hallo", "hallo", "hallo", "Hallo", "hallo", "hallo");
        given(contactRepo.findContactById(any())).willReturn(contactDTO1);
        //when
        ContactDTO contactDTO = testable.getContactById(3l);
        //then
        assertNotNull(contactDTO);
        assertEquals(contactDTO, contactDTO1);


    }


    @Test
    void getContactByEmail() {
        ContactDTO contactDTO1 = new ContactDTO("hallo", "hallo", "hallo", "Hallo", "hallo", "hallo");
        given(contactRepo.findContactByEmail(any())).willReturn(contactDTO1);
        //when
        ContactDTO contactDTO = testable.getContactByEmail("ahmed@123.com");
        //then
        assertNotNull(contactDTO);
        assertEquals(contactDTO, contactDTO1);
    }


    @Test
    void addContact() {
        ContactEntity contact = new ContactEntity(
                3l,
                "hallo",
                "hallo",
                "hallo",
                "hallo",
                "hallo",
                "hallo");
        testable.addContact(contact);
        ArgumentCaptor<ContactEntity> contactEntryArgumentCaptor = ArgumentCaptor.forClass(ContactEntity.class);
        verify(contactRepo).addContact(contactEntryArgumentCaptor.capture());
        ContactEntity captorContactEntity = contactEntryArgumentCaptor.getValue();
        assertThat(captorContactEntity.getFName()).isEqualTo(contact.getFName());
        assertThat(captorContactEntity.getLName()).isEqualTo(contact.getLName());
        assertThat(captorContactEntity.getStreet()).isEqualTo(contact.getStreet());
        assertThat(captorContactEntity.getPostcode()).isEqualTo(contact.getPostcode());
        assertThat(captorContactEntity.getEmail()).isEqualTo(contact.getEmail());
        assertThat(captorContactEntity).isEqualTo(contact);
    }

    @Test
    void updateContact() {
        ContactEntity contact = new ContactEntity(
                3l,
                "hallo",
                "hallo",
                "hallo",
                "hallo",
                "hallo",
                "hallo");
        testable.updateContact(contact);
        ArgumentCaptor<ContactEntity> contactEntryArgumentCaptor = ArgumentCaptor.forClass(ContactEntity.class);
        verify(contactRepo).updateContact(contactEntryArgumentCaptor.capture());
        ContactEntity captorContactEntity = contactEntryArgumentCaptor.getValue();
        assertThat(captorContactEntity.getFName()).isEqualTo(contact.getFName());
        assertThat(captorContactEntity.getLName()).isEqualTo(contact.getLName());
        assertThat(captorContactEntity.getStreet()).isEqualTo(contact.getStreet());
        assertThat(captorContactEntity.getPostcode()).isEqualTo(contact.getPostcode());
        assertThat(captorContactEntity.getEmail()).isEqualTo(contact.getEmail());


    }

    @Test
    void addListContacts() {
        ContactEntity contact = new ContactEntity(
                3l,
                "hallo",
                "hallo",
                "hallo",
                "hallo",
                "hallo",
                "hallo");
        //given
        List<ContactEntity> contactList = new ArrayList<>();
        contactList.add(contact);
        //When
        testable.addListContacts(contactList);
        //then
        ArgumentCaptor<List<ContactEntity>> contactEntryArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(contactRepo).addListContacts(contactEntryArgumentCaptor.capture());
        List<ContactEntity> captorContactEntity = contactEntryArgumentCaptor.getValue();
        assertThat(captorContactEntity.iterator().next().getFName()).isEqualTo(contactList.get(0).getLName());

    }


}