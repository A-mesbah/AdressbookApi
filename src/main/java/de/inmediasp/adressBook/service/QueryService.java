package de.inmediasp.adressBook.service;

import de.inmediasp.adressBook.model.ContactEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//if you want to write your private Query -->you could use this.
@Service
public class QueryService {
    @PersistenceContext
    private EntityManager entityManager;

    public ContactEntity getContact(Long id ){
        String query= "your Query";
         var result =entityManager.createNativeQuery(query, ContactEntity.class);
         return (ContactEntity) result.getSingleResult();
    }
}
