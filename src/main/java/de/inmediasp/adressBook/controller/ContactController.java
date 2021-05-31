package de.inmediasp.adressBook.controller;

import de.inmediasp.adressBook.model.Contact;
import de.inmediasp.adressBook.model.ContactEntry;
import de.inmediasp.adressBook.service.ContactServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Contact", description = "Operation for ContactService ")

public class ContactController {
    private static Logger logger = LogManager.getLogger(ContactController.class);
    @Autowired
    private ContactServiceImp contactServiceImp;

    @GetMapping("/contacts")
    @ApiOperation(value = "View a list of available Contacts ", response = Contact.class)
    public ResponseEntity<List<ContactEntry>> getAllContacts() {
        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.add("description", "online contacts ");
        var x = contactServiceImp.getAllContacts();

        return ResponseEntity.status(HttpStatus.OK).headers(httpHeader).body(x);
    }

    //you can use con/{id}but instead of @RequestParam use @PathVariable Long id
    @GetMapping("/contact/{id}")
    public ContactEntry getContactWithId(@PathVariable Long id) {
        return contactServiceImp.getContact(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED, reason = "contact Added Successfully")
    @PostMapping("/contact")
    public void addContact(@RequestBody ContactEntry contact) {
        contactServiceImp.addContact(contact);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/contacts")
    public void addContactsList(@RequestParam List<ContactEntry> contacts) {
        contactServiceImp.addListContacts(contacts);

    }

    @ResponseStatus(value = HttpStatus.OK, reason = "contact updated Successfully")
    @PutMapping("/contact")
    public void updateContact(@RequestBody ContactEntry contact) {
        contactServiceImp.addContact(contact);

    }

    @ResponseStatus(value = HttpStatus.OK, reason = "Contact successfully deleted")
    @DeleteMapping("/contact")
    public void deleteContact(@RequestParam Long id) {
        contactServiceImp.deleteContact(id);
    }

}
// return a list of contacts have the same fristName
  /*  @GetMapping("/con")
    public List<Contact> getContactByFristName(@RequestParam String fristName) {
        return contactService.getContactByFristName(fristName);

    }*/