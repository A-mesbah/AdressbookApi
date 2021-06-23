package de.inmediasp.adressBook.service.ServicesInterfaces;

import de.inmediasp.adressBook.model.ContactDTO;
import de.inmediasp.adressBook.model.ContactEntity;

import java.util.List;

public interface ContactWrite {
    public void deleteContact(Long id );
    public void addContact(ContactEntity contact);
    public void updateContact(ContactEntity contact);
    public void addListContacts(List<ContactEntity> contacts);
}
