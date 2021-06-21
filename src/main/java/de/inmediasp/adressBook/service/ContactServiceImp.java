package de.inmediasp.adressBook.service;

import de.inmediasp.adressBook.exception.ApiRequestException;
import de.inmediasp.adressBook.model.ContactEntity;
import de.inmediasp.adressBook.model.ContactDTO;
import de.inmediasp.adressBook.repository.ContactRepo;
import de.inmediasp.adressBook.service.ServicesInterfaces.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImp implements ContactService {
    private final ContactRepo contactRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public ContactServiceImp(ContactRepo contactRepo, ModelMapper modelMapper) {

        this.contactRepo = contactRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ContactDTO> getAllContacts() {

        return contactRepo.findAll()
                .stream()
                .map(contactEntity -> modelMapper.map(contactEntity, ContactDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public ContactDTO getContact(String email) {
        return
                contactRepo.findByEmail(email).map(contactEntity -> modelMapper.map(contactEntity, ContactDTO.class)))
                .orElseThrow(() -> new ApiRequestException("there is no id with this contact "));
    }

    @Override
    public void addContact(ContactEntity contact) {
        contactRepo.save(contact);
    }

    @Override
    public void addListContacts(List<ContactEntity> contacts) {
        if (contacts == null) {
            throw new ApiRequestException("addContactList  is Null");
        }
        contacts.stream()
                .findAny()
                .orElseThrow(() -> new ApiRequestException(" addContactList is Empty "));
        contactRepo.saveAll(contacts);
    }

    @Override
    public void updateContact(ContactEntity contact) {
        contactRepo.save(contact);
    }

    @Override
    public void deleteContact(String email) {
        ContactDTO contactDTO = getContact(email);
        contactRepo.deleteById(email);
    }



}


