package de.inmediasp.adressBook.Firebase.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import de.inmediasp.adressBook.exception.ApiRequestException;
import de.inmediasp.adressBook.model.ContactDTO;
import de.inmediasp.adressBook.model.ContactEntity;
import de.inmediasp.adressBook.repository.IFBContactRepo;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
@Repository
public class FBContactRepoImp implements IFBContactRepo {
    public static final String COLLECTION_NAME = "adressbook";
    public Firestore dbFirestore;
    public ModelMapper modelMapper= new ModelMapper();

    public FBContactRepoImp(Firestore dbFirestore) {
        this.dbFirestore = dbFirestore;
    }

    public List<ContactDTO> findAllContacts() {

        dbFirestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> reference = dbFirestore.collection(COLLECTION_NAME).listDocuments();
        List<ContactDTO> contacts = new ArrayList<ContactDTO>();

        reference.forEach(ref -> {

            try {
                ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = ref.get();
                DocumentSnapshot documentSnapshot = documentSnapshotApiFuture.get();
                contacts.add(documentSnapshot.toObject(ContactDTO.class));
            } catch (InterruptedException | ExecutionException interruptedException) {
                interruptedException.printStackTrace();
            }

        });

        return contacts;

    }


    public ContactDTO findContactById(Long id) {
        dbFirestore = FirestoreClient.getFirestore();
        ContactEntity contactEntity = null;
        try {
            Query query = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("id", id);
            QuerySnapshot querySnapshot = query.get().get();
            if (querySnapshot.size() < 1) {
                throw new ApiRequestException("there is no contact with this id");
            }
            contactEntity = querySnapshot.toObjects(ContactEntity.class).get(0);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return modelMapper.map(contactEntity, ContactDTO.class);
    }

    public ContactDTO findContactByEmail(String email) {
        dbFirestore = FirestoreClient.getFirestore();
        ContactDTO contactDTO = null;

        Query query = dbFirestore
                .collection(COLLECTION_NAME)
                .whereEqualTo("email", email);
        try {
            contactDTO = query.get().get().toObjects(ContactDTO.class).get(0);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return contactDTO;
    }

    //Done

    public void deleteContact(Long id) {
        ContactDTO contactDto = findContactById(id);
        if (contactDto != null) {
            dbFirestore = FirestoreClient.getFirestore();
            String email = contactDto.getEmail();
            try {
                ApiFuture<QuerySnapshot> querySnapshotApiFuture = dbFirestore.collection(COLLECTION_NAME)
                        .whereEqualTo("email", email).get();
                QuerySnapshot querySnapshot = querySnapshotApiFuture.get();
                List<QueryDocumentSnapshot> resultList = querySnapshot.getDocuments();
                resultList.stream().forEach(doc -> doc.getReference().delete());

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }


    }


    public void addContact(ContactEntity contact) {
        dbFirestore = FirestoreClient.getFirestore();
        QuerySnapshot querySnapshot = null;
        try {
            querySnapshot = dbFirestore.collection(COLLECTION_NAME).
                    whereEqualTo("email", contact.getEmail()).get().get();
            if (querySnapshot.isEmpty()) {
                contact.setId(IDGenerator.getLatestId(COLLECTION_NAME) + 1);
                dbFirestore.collection(COLLECTION_NAME).add(contact);
            } else
                throw new ApiRequestException("this email is already used");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }


    public void updateContact(ContactEntity contact) {
        dbFirestore = FirestoreClient.getFirestore();
        List<QueryDocumentSnapshot> documents=null;
        try {
             documents = dbFirestore.collection(COLLECTION_NAME)
                    .whereEqualTo("email", contact.getEmail()).get().get().getDocuments();
        } catch (InterruptedException | ExecutionException e) {

            e.printStackTrace();
        }
            if (documents.isEmpty()) {
                throw new ApiRequestException("this Item not found ");

            } else if (documents.size() > 1) {
                throw new ApiRequestException("cant update this item case there is more than item has same Email");
            } else {
                documents.get(0)
                        .getReference()
                        .set(contact);
            }
        }




    //Done

    public void addListContacts(List<ContactEntity> contacts) {
        dbFirestore = FirestoreClient.getFirestore();
        if (contacts==null) {
            throw new ApiRequestException(" The added ContactList  is null");
        }
       else if(contacts.isEmpty()) {
           throw  new ApiRequestException(" addContactList is Empty ");
        }
        contacts.stream().forEach((contactEntity) -> dbFirestore.collection(COLLECTION_NAME).add(contactEntity));


    }


}
