package de.inmediasp.adressBook.service;

import de.inmediasp.adressBook.exception.ApiRequestException;
import de.inmediasp.adressBook.model.Contact;
import de.inmediasp.adressBook.model.ContactEntry;
import de.inmediasp.adressBook.repository.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImp implements ContactService {

    private final ContactRepo contactRepo;

    @Autowired
    public ContactServiceImp(ContactRepo contactRepo){
        this.contactRepo = contactRepo;
    }


    @Override
    public List<ContactEntry> getAllContacts() {
        var entries = contactRepo.findAll();

        List results = new ArrayList<ContactEntry>();

        for (Contact contact : entries) {
            results.add(new ContactEntry(
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
    public ContactEntry getContact(Long id) {
        var result = contactRepo.findById(id).
                orElseThrow(() -> new ApiRequestException("this Id not found "));
        return new ContactEntry(
                result.getfName(),
                result.getlName(),
                result.getStreet(),
                result.getPostcode(),
                result.getCountry(),
                result.getEmail()
        );
    }

    @Override
    public void addContact(ContactEntry tempContact) {
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
    public void updateContact(ContactEntry tempContact) {
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
        try {
            contactRepo.deleteById(id);
        }catch (RuntimeException EX){
            throw new ApiRequestException("this id not exists ");
        }


    }

    @Override
    public void addListContacts(List<ContactEntry> tempContacts) {
        List<Contact> contacts = new ArrayList<>();
        for (ContactEntry contact : tempContacts) {
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

    // public List<Contact> getContactWithFristAndLastName(String fristname, String lastName) {
    //  return contactRepo.findByFNameANDLName(fristname, lastName);

    // }


}
  /* public List<Contact> getContactByFristName (String name  ){
         return contactRepo.findByFName(name );
     }*/