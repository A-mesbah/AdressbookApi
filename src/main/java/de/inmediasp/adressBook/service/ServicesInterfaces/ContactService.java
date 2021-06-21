package de.inmediasp.adressBook.service.ServicesInterfaces;

import de.inmediasp.adressBook.service.ServicesInterfaces.ContactRead;
import de.inmediasp.adressBook.service.ServicesInterfaces.ContactWrite;
import org.springframework.stereotype.Service;

@Service
public interface ContactService extends ContactWrite, ContactRead {

}
