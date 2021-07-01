package de.inmediasp.adressBook.model;

import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.database.annotations.Nullable;
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
    @Column(
            name = "id",
            nullable = false
    )
    @NotNull
    private Long id;
    @Column(
            name = "firstName",
            unique = false,
            nullable = false,
            columnDefinition = "TEXT"
    )

    private String fName;
    @Column(
            name = "lastName",
            unique = false,
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lName;
    @Column(
            name = "street",
            unique = false,
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String street;
    @Column(
            name = "postcode",
            unique = false,
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String postcode;
    @Column(
            name = "country",
            unique = false,
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String country;
    @Column(
            name = "email",
            unique = true,
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotNull
    private String email;


}