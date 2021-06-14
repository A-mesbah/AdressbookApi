package de.inmediasp.adressBook.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

@AllArgsConstructor

public class ContactDTO {
    private String fName;
    private String lName;
    private String street;
    private String postcode;
    private String country;
    private String Email;
    public ContactDTO() {
    }

    @Override
    public String toString() {
        return "Contact{" +
                "first Name='" + fName + '\'' +
                ",last Name='" + lName + '\'' +
                ", street='" + street + '\'' +
                ", postcode='" + postcode + '\'' +
                ", country='" + country + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}
