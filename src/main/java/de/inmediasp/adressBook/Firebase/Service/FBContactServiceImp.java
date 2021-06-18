
package de.inmediasp.adressBook.Firebase.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import de.inmediasp.adressBook.exception.ApiRequestException;
import de.inmediasp.adressBook.model.ContactDTO;
import de.inmediasp.adressBook.model.ContactEntity;
import de.inmediasp.adressBook.service.ServicesInterfaces.ContactService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;


@Primary
@Service
public class FBContactServiceImp implements ContactService {
    public static final String COLLECTION_NAME = "adressbook";

    Firestore dbFirestore;

    //Done
    @Override
    public List<ContactDTO> getAllContacts() {

        dbFirestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> reference = dbFirestore.collection(COLLECTION_NAME).listDocuments();
        var contacts = new ArrayList<ContactDTO>();

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

    //Done
    @Override
    public ContactDTO getContact(Long id) {
        dbFirestore = FirestoreClient.getFirestore();
        DocumentSnapshot documentSnapshot = null;
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(id.toString());
        try {
            documentSnapshot = documentReference.get().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return documentSnapshot.toObject(ContactDTO.class);
    }

    //Done
    @Override
    public void deleteContact(Long id) {
        dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> documentReference = dbFirestore.collection(COLLECTION_NAME).document(id.toString()).delete();

    }

    @Override
    public void addContact(ContactEntity contact) {
        dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection(COLLECTION_NAME).add(contact);

    }

    @Override
    public void updateContact(ContactEntity contact) {
        dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection(COLLECTION_NAME);
    }

    //Done
    @Override
    public void addListContacts(List<ContactEntity> contacts) {
        dbFirestore = FirestoreClient.getFirestore();
        if (contacts == null) {
            throw new ApiRequestException("addContactList  is Null");
        }
        contacts.stream()
                .findAny()
                .orElseThrow(() -> new ApiRequestException(" addContactList is Empty "));
        contacts.stream().forEach((contactEntity) -> dbFirestore.collection(COLLECTION_NAME).add(contactEntity));


    }
}

