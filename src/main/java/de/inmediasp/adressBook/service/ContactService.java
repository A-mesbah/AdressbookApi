package de.inmediasp.adressBook.service;

import de.inmediasp.adressBook.model.Contact;
import de.inmediasp.adressBook.model.ContactEntery;

import java.util.List;

public interface ContactService {

    public List<ContactEntery> getAllContacts();

    public ContactEntery getContact(Long id);


    public void addContact(ContactEntery contact);

    public void updateContact(ContactEntery contact);

    public void deleteContact(Long id);

    public void addListContacts(List<ContactEntery > contacts);
}
