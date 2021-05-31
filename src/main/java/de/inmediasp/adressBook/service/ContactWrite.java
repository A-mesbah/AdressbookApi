package de.inmediasp.adressBook.service;

import de.inmediasp.adressBook.model.ContactEntry;

import java.util.List;

public interface ContactWrite {
    public void deleteContact(Long id);
    public void addContact(ContactEntry contact);
    public void updateContact(ContactEntry contact);
    public void addListContacts(List<ContactEntry> contacts);
}
