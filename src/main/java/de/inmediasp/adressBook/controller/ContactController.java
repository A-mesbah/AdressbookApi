package de.inmediasp.adressBook.controller;

import de.inmediasp.adressBook.model.ContactEntity;
import de.inmediasp.adressBook.model.ContactDTO;
import de.inmediasp.adressBook.service.ServicesInterfaces.ContactService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ContactController {
    private static Logger logger = LogManager.getLogger(ContactController.class);
    private final ContactService contactServiceImp;

    @Autowired
    public ContactController(ContactService contactServiceImp) {
        this.contactServiceImp = contactServiceImp;
    }

    @GetMapping("/contacts")
    public ResponseEntity getAllContacts() {
        List<ContactDTO> contactsList = contactServiceImp.getAllContacts();
        return ResponseEntity.status(HttpStatus.OK).body(contactsList);
    }

   /* @GetMapping("/contact/{email}")
    public ResponseEntity getContactWithId(@PathVariable String email) {
        ContactDTO contactDTO = contactServiceImp.getContact(email);
        if (contactDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(contactDTO);
        } else return ResponseEntity.status(HttpStatus.NO_CONTENT).body("There is  no data for this id ");
    }*/

    @PostMapping("/contact")
    public ResponseEntity addContact(@RequestBody ContactEntity contact) {
        contactServiceImp.addContact(contact);
        ModelMapper modelMapper = new ModelMapper();
        ContactDTO contactDTO = modelMapper.map(contact, ContactDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(contactDTO.toString() + "\nthis contact is successfully added");
    }

    @PostMapping("/contacts")
    public ResponseEntity addContactsList(@RequestBody List<ContactEntity> contacts) {
        if (contacts!=null){
            contactServiceImp.addListContacts(contacts);
            return ResponseEntity.status(HttpStatus.CREATED).body("the  contactList is successfully added");
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("the  contactList is failed to   add");



    }

    @PutMapping("/contact")
    public ResponseEntity updateContact(@RequestBody ContactEntity contact) {
        contactServiceImp.updateContact(contact);
        return ResponseEntity.status(HttpStatus.OK).body("contact updated Successfully");
    }

  /*  @DeleteMapping("/contact/{email}")
    public ResponseEntity deleteContact(@PathVariable String email) {
       contactServiceImp.deleteContact(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Contact successfully deleted");
    }*/

}
