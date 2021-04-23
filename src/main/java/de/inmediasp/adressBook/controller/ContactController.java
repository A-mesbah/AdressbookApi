package de.inmediasp.adressBook.controller;

import de.inmediasp.adressBook.model.Contact;
import de.inmediasp.adressBook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts")
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    //you can use con/{id}but instead of @RequestParam use @PathVariable Long id
    @GetMapping("/contact/{id}")
    public Contact getContactWithId(@PathVariable Long id) {
        return contactService.getContact(id);
    }


    @PostMapping("/contact")
    public String addContact(@RequestBody Contact contact) {
        contactService.addContact(contact);
        return "contact added Successfully";
    }
    @PostMapping("/addcontacts")
    public String addContactsList(@RequestParam List<Contact>contacts){
        contactService.addListContacts(contacts);
        return "The list of contacts added successfully";
    }

    @PutMapping("/contact")
    public String updateContact(@RequestBody Contact contact) {
        contactService.addContact(contact);
        return "contact updated Successfully";
    }

    @DeleteMapping("/contact")
    public String deleteContact(@RequestParam Long id) {
        contactService.deleteContact(id);
        return "Contact successfully deleted";
    }

}
// return a list of contacts have the same fristName
  /*  @GetMapping("/con")
    public List<Contact> getContactByFristName(@RequestParam String fristName) {
        return contactService.getContactByFristName(fristName);

    }*/
