package de.inmediasp.adressBook.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ContactEntery {
    private String fName;
    private String lName;
    private String street;
    private String postcode;
    private String country;
    private String Email;
}
