
package de.inmediasp.adressBook.Firebase.Service;
import de.inmediasp.adressBook.model.ContactDTO;
import de.inmediasp.adressBook.model.ContactEntity;
import de.inmediasp.adressBook.repository.IFBContactRepo;
import de.inmediasp.adressBook.service.ServicesInterfaces.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;


@Primary
@Service
public class FBContactServiceImp implements ContactService {
   private IFBContactRepo ifbContactRepo;

    @Autowired
    public FBContactServiceImp(IFBContactRepo ifbContactRepo) {
        this.ifbContactRepo = ifbContactRepo;

    }

    @Override
    public List<ContactDTO> getAllContacts() {
       return ifbContactRepo.findAllContacts();
    }

    @Override
    public ContactDTO getContactById(Long id) {
      return ifbContactRepo.findContactById(id);
    }

    @Override
    public ContactDTO getContactByEmail(String name) {
        return ifbContactRepo.findContactByEmail(name );
    }

    @Override
    public void deleteContact(Long id) {
        ifbContactRepo.deleteContact(id);

    }

    @Override
    public void addContact(ContactEntity contact) {
        ifbContactRepo.addContact(contact);

    }

    @Override
    public void updateContact(ContactEntity contact) {
        ifbContactRepo.updateContact(contact);

    }

    @Override
    public void addListContacts(List<ContactEntity> contacts) {
        ifbContactRepo.addListContacts(contacts);

    }
}
