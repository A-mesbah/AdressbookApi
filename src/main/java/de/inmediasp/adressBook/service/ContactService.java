package de.inmediasp.adressBook.service;

import de.inmediasp.adressBook.model.Contact;

import java.util.List;

public interface ContactService {

    public List<Contact> getAllContacts();

    public Contact getContact(Long id);


    public void addContact(Contact contact);

    public void updateContact(Contact contact);

    public void deleteContact(Long id);

    public void addListContacts(List<Contact> contacts);
}
