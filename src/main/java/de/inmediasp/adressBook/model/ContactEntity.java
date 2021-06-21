package de.inmediasp.adressBook.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contacts")
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "firstName")
    private String fName;
    private String lName;
    private String street;
    private String postcode;
    private String country;
    @NotNull
    private String Email;
}