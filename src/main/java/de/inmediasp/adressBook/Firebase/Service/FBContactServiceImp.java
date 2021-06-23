
package de.inmediasp.adressBook.Firebase.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import de.inmediasp.adressBook.exception.ApiRequestException;
import de.inmediasp.adressBook.model.ContactDTO;
import de.inmediasp.adressBook.model.ContactEntity;
import de.inmediasp.adressBook.service.ServicesInterfaces.ContactRead;
import de.inmediasp.adressBook.service.ServicesInterfaces.ContactService;
import javassist.NotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


@Primary
@Service
public class FBContactServiceImp implements ContactService {
    public static final String COLLECTION_NAME = "adressbook";
    Firestore dbFirestore;
    @Override
    public List<ContactDTO> getAllContacts() {

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


    @Override
    public ContactDTO getContactById(Long id) {
        dbFirestore = FirestoreClient.getFirestore();
        ContactDTO contactDTO = null;
        Query query = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("id", id);
        try {
            contactDTO = query.get().get().toObjects(ContactDTO.class).get(0);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (contactDTO == null) {
            throw new ApiRequestException("there is no contact with this id ton delete ");
        }
        return contactDTO;
    }

    public ContactDTO getContactByEmail(String email) {
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
    @Override
    public void deleteContact(Long id) {
        ContactDTO contactDto = getContactById(id);
        if (contactDto != null) {
            dbFirestore = FirestoreClient.getFirestore();
            String email = contactDto.getEmail();
            try {
                ApiFuture<QuerySnapshot>querySnapshotApiFuture = dbFirestore.collection(COLLECTION_NAME)
                        .whereEqualTo("email",email).get();
                QuerySnapshot querySnapshot=querySnapshotApiFuture.get();
               List<QueryDocumentSnapshot> resultList = querySnapshot.getDocuments();
               resultList.stream().forEach(doc->doc.getReference().delete());

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void addContact(ContactEntity contact) {
        dbFirestore = FirestoreClient.getFirestore();
        QuerySnapshot x = null;
        try {
            x = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("email", contact.getEmail()).get().get();
            if (x.isEmpty()) {
                contact.setId(getLatestId() + 1);
                dbFirestore.collection(COLLECTION_NAME).add(contact);
            } else
                throw new ApiRequestException("this email is already used");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void updateContact(ContactEntity contact) {
        dbFirestore = FirestoreClient.getFirestore();
        try {
            List<QueryDocumentSnapshot> documents = dbFirestore.collection(COLLECTION_NAME)
                    .whereEqualTo("email", contact.getEmail()).get().get().getDocuments();

            if (documents.isEmpty()) {
                throw new NotFoundException("this Item not found ");
            } else if (documents.size() > 1) {
                throw new ApiRequestException("cant update this item case there isa more than item has same Email");
            } else {
                documents.get(0).getReference().set(contact);
            }
        } catch (InterruptedException | ExecutionException | NotFoundException e) {


            e.printStackTrace();
        }


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

    private long getLatestId() {

        dbFirestore = FirestoreClient.getFirestore();
        List<QueryDocumentSnapshot> resultList = new ArrayList<>();
        QuerySnapshot collectionReference = null;
        try {
            collectionReference = dbFirestore.
                    collection(COLLECTION_NAME).orderBy("id", Query.Direction.DESCENDING).get().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<ContactEntity>contactEntityList = collectionReference.toObjects(ContactEntity.class);
        return contactEntityList.get(0).getId();

    }
}
