package de.inmediasp.adressBook.controller;

import de.inmediasp.adressBook.model.Contact;
import de.inmediasp.adressBook.service.ContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Contact", description = "Operation for ContactService ")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts")
    @ApiOperation(value = "View a list of available Contacts ", response = Contact.class)
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    //you can use con/{id}but instead of @RequestParam use @PathVariable Long id
    @GetMapping("/contact/{id}")
    public Contact getContactWithId(@PathVariable Long id) {
        return contactService.getContact(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED, reason = "contact Added Successfully")
    @PostMapping("/contact")
    public void addContact(@RequestBody Contact contact) {
        contactService.addContact(contact);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/contacts")
    public void addContactsList(@RequestParam List<Contact> contacts) {
        contactService.addListContacts(contacts);

    }

    @ResponseStatus(value = HttpStatus.OK, reason = "contact updated Successfully")
    @PutMapping("/contact")
    public void updateContact(@RequestBody Contact contact) {
        contactService.addContact(contact);

    }

    @ResponseStatus(value = HttpStatus.OK, reason = "Contact successfully deleted")
    @DeleteMapping("/contact")
    public void deleteContact(@RequestParam Long id) {
        contactService.deleteContact(id);
    }

}
// return a list of contacts have the same fristName
  /*  @GetMapping("/con")
    public List<Contact> getContactByFristName(@RequestParam String fristName) {
        return contactService.getContactByFristName(fristName);

    }*/