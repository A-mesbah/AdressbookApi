package de.inmediasp.adressBook.service.ServicesInterfaces;

import de.inmediasp.adressBook.model.ContactDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ContactRead {
    public List<ContactDTO> getAllContacts();
    public ContactDTO getContact(long id) ;
}
