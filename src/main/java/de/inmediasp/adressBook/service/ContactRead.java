package de.inmediasp.adressBook.service;

import de.inmediasp.adressBook.model.ContactEntry;

import java.util.List;

public interface ContactRead {
    public List<ContactEntry> getAllContacts();
    public ContactEntry getContact(Long id);
}
