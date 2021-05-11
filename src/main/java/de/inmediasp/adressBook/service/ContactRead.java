package de.inmediasp.adressBook.service;

import de.inmediasp.adressBook.model.ContactEntery;

import java.util.List;

public interface ContactRead {
    public List<ContactEntery> getAllContacts();
    public ContactEntery getContact(Long id);
}
