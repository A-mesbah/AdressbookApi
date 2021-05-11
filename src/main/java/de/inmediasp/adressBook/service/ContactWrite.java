package de.inmediasp.adressBook.service;

import de.inmediasp.adressBook.model.ContactEntery;

import java.util.List;

public interface ContactWrite {
    public void deleteContact(Long id);
    public void addContact(ContactEntery contact);
    public void updateContact(ContactEntery contact);
    public void addListContacts(List<ContactEntery> contacts);
}
