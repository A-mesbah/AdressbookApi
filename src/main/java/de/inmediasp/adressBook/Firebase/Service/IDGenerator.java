package de.inmediasp.adressBook.Firebase.Service;

import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import de.inmediasp.adressBook.model.ContactEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class IDGenerator {
    public static long getLatestId(String collectionName) {

        var dbFirestore = FirestoreClient.getFirestore();
        List<QueryDocumentSnapshot> resultList = new ArrayList<>();
        QuerySnapshot collectionReference = null;
        try {
            collectionReference = dbFirestore.
                    collection(collectionName).
                    orderBy("id", Query.Direction.DESCENDING)
                    .get().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<ContactEntity> contactEntityList = collectionReference.toObjects(ContactEntity.class);
        return contactEntityList.get(0).getId();

    }
}
