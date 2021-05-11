package de.inmediasp.adressBook.service;

import de.inmediasp.adressBook.model.Contact;
import de.inmediasp.adressBook.model.ContactEntery;
import de.inmediasp.adressBook.repository.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImp implements ContactService {
    @Autowired
   private  ContactRepo contactRepo;



    @Override
    public List<ContactEntery> getAllContacts() {
        var entries = contactRepo.findAll();

        List results = new ArrayList<ContactEntery>();

        for (Contact contact : entries) {
            results.add(new ContactEntery(
                            contact.getfName(),
                            contact.getfName(),
                            contact.getStreet(),
                            contact.getPostcode(),
                            contact.getCountry(),
                            contact.getEmail()
                    )
            );

        }
        return results;

    }

    @Override
    public ContactEntery getContact(Long id) {
        var result = contactRepo.findById(id).orElseThrow(() -> null);
        return new ContactEntery(
                result.getfName(),
                result.getlName(),
                result.getStreet(),
                result.getPostcode(),
                result.getCountry(),
                result.getEmail()
        );
    }

    @Override
    public void addContact(ContactEntery tempContact) {
        Contact con = new Contact(
                tempContact.getFName(),
                tempContact.getLName(),
                tempContact.getStreet(),
                tempContact.getPostcode(),
                tempContact.getCountry(),
                tempContact.getEmail());
        contactRepo.save(con);
    }

    @Override
    public void updateContact(ContactEntery tempContact) {
        Contact con = new Contact(
                tempContact.getFName(),
                tempContact.getLName(),
                tempContact.getStreet(),
                tempContact.getPostcode(),
                tempContact.getCountry(),
                tempContact.getEmail());
        contactRepo.save(con);
    }

    @Override
    public void deleteContact(Long id) {

        contactRepo.deleteById(id);
    }

    @Override
    public void addListContacts(List<ContactEntery> tempContacts) {
        List<Contact> contacts = new ArrayList<>();
        for (ContactEntery contact : tempContacts) {
            contacts.add(new Contact(
                            contact.getFName(),
                            contact.getLName(),
                            contact.getStreet(),
                            contact.getPostcode(),
                            contact.getCountry(),
                            contact.getEmail()
                    )
            );

        }
        contactRepo.saveAll(contacts);
    }

}
  /* public List<Contact> getContactByFristName (String name  ){
         return contactRepo.findByFName(name );
     }*/