package de.inmediasp.adressBook.service;
import de.inmediasp.adressBook.model.ContactDTO;
import de.inmediasp.adressBook.model.ContactEntity;
import de.inmediasp.adressBook.repository.ContactRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
        contact = new ContactEntity("hallo", "hallo", "hallo", "Hallo", "hallo", "hallo");
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

    @Test

    void getContact() {
        //given
        ContactEntity contact = new ContactEntity("hallo", "hallo", "hallo", "Hallo", "hallo", "hallo");
        given(contactRepo.findById(any())).willReturn(Optional.of(contact));
        var contactDTO = testable.getContact(3L);
        assertNotNull(contactDTO);
       assertEquals("hallo", contactDTO.getFName());

    }

    //Done
    @Test
    void addContact() {
        testable.addContact(contact);
        ArgumentCaptor<ContactEntity> contactEntryArgumentCaptor = ArgumentCaptor.forClass(ContactEntity.class);
        verify(contactRepo).save(contactEntryArgumentCaptor.capture());
        ContactEntity captorContactEntity = contactEntryArgumentCaptor.getValue();
        assertThat(captorContactEntity.getfName()).isEqualTo(contact.getfName());
        assertThat(captorContactEntity.getlName()).isEqualTo(contact.getlName());
        assertThat(captorContactEntity.getStreet()).isEqualTo(contact.getStreet());
        assertThat(captorContactEntity.getPostcode()).isEqualTo(contact.getPostcode());
        assertThat(captorContactEntity.getEmail()).isEqualTo(contact.getEmail());


    }

    @Test
    @Disabled
        // I cant test this Method because :(findAny-findFirst )in ContactServiceImp
    void addListContacts() {
        List<ContactEntity> contactList = new ArrayList<>();
        contactList.add(contact);
        testable.addListContacts(contactList);
        ArgumentCaptor<ContactEntity> contactEntryArgumentCaptor = ArgumentCaptor.forClass(ContactEntity.class);
        verify(contactRepo).save(contactEntryArgumentCaptor.capture());
        ContactEntity captorContactEntity = contactEntryArgumentCaptor.getValue();
        assertThat(captorContactEntity.getfName()).isEqualTo(contactList.get(0).getlName());

    }

    //Done
    @Test
    void updateContact() {
        testable.updateContact(contact);
        ArgumentCaptor<ContactEntity> contactEntryArgumentCaptor = ArgumentCaptor.forClass(ContactEntity.class);
        verify(contactRepo).save(contactEntryArgumentCaptor.capture());
        ContactEntity captorContactEntity = contactEntryArgumentCaptor.getValue();
        assertThat(captorContactEntity.getfName()).isEqualTo(contact.getfName());
        assertThat(captorContactEntity.getlName()).isEqualTo(contact.getlName());
        assertThat(captorContactEntity.getStreet()).isEqualTo(contact.getStreet());
        assertThat(captorContactEntity.getPostcode()).isEqualTo(contact.getPostcode());
        assertThat(captorContactEntity.getEmail()).isEqualTo(contact.getEmail());
    }

    @Test
    // I cant test it because (findAny-findFirst )in ContactServiceImp
    void deleteContact() {
    }

}