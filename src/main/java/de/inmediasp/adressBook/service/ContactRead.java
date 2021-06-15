package de.inmediasp.adressBook.service;

import de.inmediasp.adressBook.model.ContactDTO;

import java.util.List;

public interface ContactRead {
    public List<ContactDTO> getAllContacts();
    public ContactDTO getContact(Long id);
}
