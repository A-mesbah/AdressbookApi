package de.inmediasp.adressBook.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "firstName")
    private String fName;
    private String lName;
    private String street;
    private String postcode;
    private String country;
    private String Email;

    public Contact() {
    }

    public Contact(String fName, String lName, String street, String postcode, String country, String email) {
        this.fName = fName;
        this.lName = lName;
        this.street = street;
        this.postcode = postcode;
        this.country = country;
        Email = email;
    }

    public Contact(Long id, String fName, String lName, String street, String postcode, String country, String email) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.street = street;
        this.postcode = postcode;
        this.country = country;
        Email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
