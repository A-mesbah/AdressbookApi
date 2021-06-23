package de.inmediasp.adressBook.service.ServicesInterfaces;

import de.inmediasp.adressBook.model.ContactDTO;
import de.inmediasp.adressBook.model.ContactEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ContactRead {
    public List<ContactDTO> getAllContacts();
    public ContactDTO getContactById(Long id) ;
   public  ContactDTO getContactByEmail(String name );
}
