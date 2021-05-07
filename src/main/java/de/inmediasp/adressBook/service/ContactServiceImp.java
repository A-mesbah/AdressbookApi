package de.inmediasp.adressBook.service;

import de.inmediasp.adressBook.model.Contact;
import de.inmediasp.adressBook.repository.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImp implements ContactService {
    @Autowired
   private  ContactRepo contactRepo;



    @Override
    public List<Contact> getAllContacts() {
        return contactRepo.findAll();
    }

    @Override
    public Contact getContact(Long id) {
        return contactRepo.findById(id).orElseThrow(null);
    }

    @Override

    public void addContact(Contact contact) {
        contactRepo.save(contact);
    }

    @Override
    public void updateContact(Contact contact) {
        contactRepo.save(contact);
    }

    @Override
    public void deleteContact(Long id) {
        contactRepo.deleteById(id);
    }

    @Override
    public void addListContacts(List<Contact> contacts) {
        contactRepo.saveAll(contacts);
    }

}
  /* public List<Contact> getContactByFristName (String name  ){
         return contactRepo.findByFName(name );
     }*/