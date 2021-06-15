
package de.inmediasp.adressBook.Firebase.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import de.inmediasp.adressBook.model.ContactDTO;
import de.inmediasp.adressBook.model.ContactEntity;
import de.inmediasp.adressBook.service.ContactService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;


@Primary
@Service
public class FBContactServiceImp implements ContactService {

        public static final String COLLECTION_NAME ="adressbook";


        public String savePatientDetails(ContactDTO contactDTO) throws InterruptedException, ExecutionException {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COLLECTION_NAME).document(contactDTO.getFName()).set(contactDTO);
            return collectionsApiFuture.get().getUpdateTime().toString();
        }
        public ContactDTO getPatientDetails(String name) throws InterruptedException, ExecutionException {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(name);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();
            ContactDTO contactDTO = null;
            if(document.exists()) {
                contactDTO = document.toObject(ContactDTO.class);
                return contactDTO;
            }else {
                return null;
            }

        }
        public String updatePatientDetails(ContactDTO contactDto) throws InterruptedException, ExecutionException {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COLLECTION_NAME).document(contactDto.getFName()).set(contactDto);
            return collectionsApiFuture.get().getUpdateTime().toString();
        }
        public String deletePatient(String name) {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> writeResult = dbFirestore.collection(COLLECTION_NAME).document(name).delete();
            return "Document with Patient ID "+name+" has been deleted";
        }

    @Override
    public List<ContactDTO> getAllContacts() {
        return null;
    }

    @Override
    public ContactDTO getContact(Long id) {
        return null;
    }

    @Override
    public void deleteContact(Long id) {

    }

    @Override
    public void addContact(ContactEntity contact) {

    }

    @Override
    public void updateContact(ContactEntity contact) {

    }

    @Override
    public void addListContacts(List<ContactEntity> contacts) {

    }
}

