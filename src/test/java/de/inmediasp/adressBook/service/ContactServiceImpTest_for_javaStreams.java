package de.inmediasp.adressBook.service;

import de.inmediasp.adressBook.exception.ApiRequestException;
import de.inmediasp.adressBook.model.ContactDTO;
import de.inmediasp.adressBook.model.ContactEntity;
import de.inmediasp.adressBook.repository.ContactRepo;
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
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;



@ExtendWith(MockitoExtension.class)
class ContactServiceImpTest_for_javaStreams {

    ContactServiceImp testable;
    @Mock
    private ContactRepo contactRepo;
    @Mock
    private ModelMapper modelMapper;
    private ContactEntity contact;

    @BeforeEach
    void setup() {

        MockitoAnnotations.openMocks(this);
        testable = new ContactServiceImp(contactRepo, modelMapper);
        contact = new ContactEntity(3l,"hallo","hallo","hallo","hallo","hallo","hallo");
    }

    //Done
    @Test
    void getAllContacts() {
        List<ContactEntity> mockedList = new ArrayList<>();
        mockedList.add(contact);
        //given
        given(contactRepo.findAll()).willReturn(mockedList);
        //then
        List<ContactDTO> result = testable.getAllContacts();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mockedList.size(), result.size());
        assertEquals(mockedList.stream()
                        .map(contactEntity -> modelMapper.map(contactEntity, ContactDTO.class))
                        .collect(Collectors.toList()),
                result);

    }

    //Done
    @Test
    void getContact() {
        //given
        ContactDTO contactDTO1 = new ContactDTO("hallo", "hallo", "hallo", "Hallo", "hallo", "hallo");
        given(contactRepo.findById(any())).willReturn(Optional.of(contact));
        given(modelMapper.map(contact, ContactDTO.class)).willReturn(contactDTO1);
        //when

        ContactDTO contactDTO = testable.getContactById(3l);
        //then
        assertNotNull(contactDTO);
        assertEquals(contactDTO, contactDTO1);

    }

    //Done
    @Test
    void addContact() {
        testable.addContact(contact);
        ArgumentCaptor<ContactEntity> contactEntryArgumentCaptor = ArgumentCaptor.forClass(ContactEntity.class);
        verify(contactRepo).save(contactEntryArgumentCaptor.capture());
        ContactEntity captorContactEntity = contactEntryArgumentCaptor.getValue();
        assertThat(captorContactEntity.getFName()).isEqualTo(contact.getFName());
        assertThat(captorContactEntity.getLName()).isEqualTo(contact.getLName());
        assertThat(captorContactEntity.getStreet()).isEqualTo(contact.getStreet());
        assertThat(captorContactEntity.getPostcode()).isEqualTo(contact.getPostcode());
        assertThat(captorContactEntity.getEmail()).isEqualTo(contact.getEmail());


    }

    @Test
    void addListContacts() {
        //given
        List<ContactEntity> contactList = new ArrayList<>();
        contactList.add(contact);
        //When
        testable.addListContacts(contactList);
        //then
        ArgumentCaptor<Iterable<ContactEntity>> contactEntryArgumentCaptor = ArgumentCaptor.forClass(Iterable.class);
        verify(contactRepo).saveAll(contactEntryArgumentCaptor.capture());
        Iterable<ContactEntity> captorContactEntity = contactEntryArgumentCaptor.getValue();
        assertThat(captorContactEntity.iterator().next().getFName()).isEqualTo(contactList.get(0).getLName());

    }
@Test
void ThrowExceptionWhenListIsNull(){
    List<ContactEntity> contactList = null;
    ApiRequestException apiRequestException = assertThrows(ApiRequestException.class, () -> testable.addListContacts(contactList));
    assertEquals("addContactList  is Null", apiRequestException.getMessage());
}
    @Test
    void ThrowExceptionWhenListIsEmpty (){
        List<ContactEntity> contactList = new ArrayList<>();
        ApiRequestException apiRequestException = assertThrows(ApiRequestException.class, () -> testable.addListContacts(contactList));
        assertEquals(" addContactList is Empty ", apiRequestException.getMessage());
    }
    //Done
    @Test
    void updateContact() {
        testable.updateContact(contact);
        ArgumentCaptor<ContactEntity> contactEntryArgumentCaptor = ArgumentCaptor.forClass(ContactEntity.class);
        verify(contactRepo).save(contactEntryArgumentCaptor.capture());
        ContactEntity captorContactEntity = contactEntryArgumentCaptor.getValue();
        assertThat(captorContactEntity.getFName()).isEqualTo(contact.getFName());
        assertThat(captorContactEntity.getLName()).isEqualTo(contact.getLName());
        assertThat(captorContactEntity.getStreet()).isEqualTo(contact.getStreet());
        assertThat(captorContactEntity.getPostcode()).isEqualTo(contact.getPostcode());
        assertThat(captorContactEntity.getEmail()).isEqualTo(contact.getEmail());
    }

    @Test
        // I cant test it because (findAny-findFirst )in ContactServiceImp
        //TODO Thomas : can you please write a test for this Method
    void deleteContact() {

    }



}
