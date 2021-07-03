package de.inmediasp.adressBook.service;


import de.inmediasp.adressBook.model.ContactEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Service
public class QueryService {
    @PersistenceContext
    private EntityManager entityManager;

    public ContactEntity getContact(String email){
        String query= "SELECT * FROM adressbook where email=";
         var result =entityManager.createNativeQuery(query, ContactEntity.class);
         return (ContactEntity) result.getSingleResult();
    }
}


