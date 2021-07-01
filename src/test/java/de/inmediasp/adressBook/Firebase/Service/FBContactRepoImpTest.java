package de.inmediasp.adressBook.Firebase.Service;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import de.inmediasp.adressBook.exception.ApiRequestException;
import de.inmediasp.adressBook.model.ContactDTO;
import de.inmediasp.adressBook.model.ContactEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FBContactRepoImpTest {

    @Mock
    private Firestore dbFireStore;
    @Mock
    private ModelMapper mockedModelMapper;
    @Mock
    private CollectionReference mockedCollection;
    @Mock
    private DocumentReference mockedDocumentReference;
    @Mock
    private Iterable<DocumentReference> mockedList;
    @Mock
    private Query mockedQuery;
    @Mock
    private QuerySnapshot mockedQuerySnapShot;
    @Mock
    private ApiFuture mockedApiFuture;
    @Mock
    private List<QueryDocumentSnapshot> mockedQueryDocumentSnapshot;
    @Mock
    private QueryDocumentSnapshot mockedQueryDocumentSnapShot1;

    private FBContactRepoImp testable;
    private ContactEntity contact;

    @BeforeEach
    void setup() {


        MockitoAnnotations.openMocks(this);
        testable = new FBContactRepoImp(dbFireStore);
        contact = new ContactEntity(
                3L,
                "hallo",
                "hallo",
                "hallo",
                "hallo",
                "hallo",
                "hallo");

    }

    //Done
    @Test
    void whenInvokeGetAllContacts_WillGetListOfContactsIFFound() {
        MockedStatic mockedClient = Mockito.mockStatic(FirestoreClient.class);
        //Wie sollen sich die gemockten Objekte verhalten?
        mockedClient.when(() -> FirestoreClient.getFirestore()).thenReturn(dbFireStore);
        given(dbFireStore.collection(any())).willReturn(mockedCollection);
        given(mockedCollection.listDocuments()).willReturn(mockedList);
        doNothing().when(mockedList).forEach(any());
        List<ContactDTO> result = testable.findAllContacts();
        assertNotNull(result);
        assertTrue(result.isEmpty());
        //Wie oft wurden bestimmte Methoden aufgerufen?
        verify(dbFireStore, times(1)).collection(any());
        verify(mockedCollection, times(1)).listDocuments();
    }

    //Done
    @Test
    void whenInvokeGetContactById_WillGetaContactIfFound() throws ExecutionException, InterruptedException {
        ContactDTO contactDTO = new ContactDTO(
                "hallo",
                "hallo",
                "hallo",
                "hallo",
                "hallo",
                "hallo");

        MockedStatic mockedClient = Mockito.mockStatic(FirestoreClient.class);
        mockedClient.when(FirestoreClient::getFirestore).thenReturn(dbFireStore);
        given(dbFireStore.collection(anyString())).willReturn(mockedCollection);
        given(mockedCollection.whereEqualTo(anyString(), anyLong())).willReturn(mockedQuery);
        given(mockedQuery.get()).willReturn(mockedApiFuture);
        given(mockedApiFuture.get()).willReturn(mockedQuerySnapShot);
        given(mockedQuerySnapShot.size()).willReturn(1);
        given(mockedQuerySnapShot.toObjects(ContactEntity.class)).willReturn(Collections.singletonList(contact));
        assertEquals(contactDTO.getFName(), testable.findContactById(3L).getFName());

    }

    //Done
    @Test
    void whenInvokeGetContactById_WillThrowApiExceptionIfNotFound() throws ExecutionException, InterruptedException {
        ContactDTO contactDTO = new ContactDTO(
                "hallo",
                "hallo",
                "hallo",
                "hallo",
                "hallo",
                "hallo");
        MockedStatic mockedClient = Mockito.mockStatic(FirestoreClient.class);
        mockedClient.when(FirestoreClient::getFirestore).thenReturn(dbFireStore);
        given(dbFireStore.collection(anyString())).willReturn(mockedCollection);
        given(mockedCollection.whereEqualTo(anyString(), anyLong())).willReturn(mockedQuery);
        given(mockedQuery.get()).willReturn(mockedApiFuture);
        given(mockedApiFuture.get()).willReturn(mockedQuerySnapShot);
        assertThrows(ApiRequestException.class, () -> testable.findContactById(1L));
    }

    //Done
    @Test
    void whenInvokeGetContactByEmail_WillGetaContactIfFound() throws ExecutionException, InterruptedException {
        ContactDTO contact = new ContactDTO(
                "hallo",
                "hallo",
                "hallo",
                "hallo",
                "hallo",
                "hallo");
        MockedStatic mockedClient = Mockito.mockStatic(FirestoreClient.class);
        mockedClient.when(FirestoreClient::getFirestore).thenReturn(dbFireStore);
        given(dbFireStore.collection(anyString())).willReturn(mockedCollection);
        given(mockedCollection.whereEqualTo(anyString(), anyString())).willReturn(mockedQuery);
        given(mockedQuery.get()).willReturn(mockedApiFuture);
        given(mockedApiFuture.get()).willReturn(mockedQuerySnapShot);
        given(mockedQuerySnapShot.toObjects(ContactDTO.class)).willReturn(Collections.singletonList(contact));
        assertEquals(contact, testable.findContactByEmail("ahmed@123.com"));

    }


    @Test
    void whenInvokeAddContact_makeSureItIsAlreadyAddedToFBDB() throws ExecutionException, InterruptedException {

        MockedStatic mockedIdGenerator = Mockito.mockStatic(IDGenerator.class);
        mockedIdGenerator.when(() -> IDGenerator.getLatestId(anyString())).thenReturn(3L);
        MockedStatic mockedClient = Mockito.mockStatic(FirestoreClient.class);
        mockedClient.when(FirestoreClient::getFirestore).thenReturn(dbFireStore);
        given(dbFireStore.collection(anyString())).willReturn(mockedCollection);
        given(mockedCollection.whereEqualTo(anyString(), anyString())).willReturn(mockedQuery);
        given(mockedQuery.get()).willReturn(mockedApiFuture);
        given(mockedApiFuture.get()).willReturn(mockedQuerySnapShot);
        given(mockedQuerySnapShot.isEmpty()).willReturn(true);
        given(mockedCollection.add(any(ContactEntity.class))).willReturn(null);
        testable.addContact(contact);
        assertEquals(4, contact.getId());
        verify(mockedCollection, times(1)).add(any(ContactEntity.class));
        verify(dbFireStore, times(2)).collection(anyString());


    }

    @Test
    void whenWantToUpdateContactButThereAreMoreContactWithTheSameEmail_shouldGetApiException() throws ExecutionException, InterruptedException {
        MockedStatic mockedClient = Mockito.mockStatic(FirestoreClient.class);
        mockedClient.when(FirestoreClient::getFirestore).thenReturn(dbFireStore);
        given(dbFireStore.collection(anyString())).willReturn(mockedCollection);
        given(mockedCollection.whereEqualTo(anyString(), anyString())).willReturn(mockedQuery);
        given(mockedQuery.get()).willReturn(mockedApiFuture);
        given(mockedApiFuture.get()).willReturn(mockedQuerySnapShot);
        given(mockedQuerySnapShot.getDocuments()).willReturn(mockedQueryDocumentSnapshot);
        given(mockedQueryDocumentSnapshot.size()).willReturn(2);
        assertThrows(ApiRequestException.class, () -> testable.updateContact(contact));
    }

    @Test
    void whenWantToUpdateContactButThereIsNoContactWithTheSameEmail_shouldGetApiException() throws ExecutionException, InterruptedException {
        MockedStatic mockedClient = Mockito.mockStatic(FirestoreClient.class);
        mockedClient.when(FirestoreClient::getFirestore).thenReturn(dbFireStore);
        given(dbFireStore.collection(anyString())).willReturn(mockedCollection);
        given(mockedCollection.whereEqualTo(anyString(), anyString())).willReturn(mockedQuery);
        given(mockedQuery.get()).willReturn(mockedApiFuture);
        given(mockedApiFuture.get()).willReturn(mockedQuerySnapShot);
        given(mockedQuerySnapShot.getDocuments()).willReturn(mockedQueryDocumentSnapshot);
        given(mockedQueryDocumentSnapshot.isEmpty()).willReturn(true);
        assertThrows(ApiRequestException.class, () -> testable.updateContact(contact));

    }

    @Test
    void whenWantToUpdateContactAndFoundInDBShouldAdded() throws ExecutionException, InterruptedException {
        MockedStatic mockedClient = Mockito.mockStatic(FirestoreClient.class);
        mockedClient.when(FirestoreClient::getFirestore).thenReturn(dbFireStore);
        given(dbFireStore.collection(anyString())).willReturn(mockedCollection);
        given(mockedCollection.whereEqualTo(anyString(), anyString())).willReturn(mockedQuery);
        given(mockedQuery.get()).willReturn(mockedApiFuture);
        given(mockedApiFuture.get()).willReturn(mockedQuerySnapShot);
        given(mockedQuerySnapShot.getDocuments()).willReturn(mockedQueryDocumentSnapshot);
        given(mockedQueryDocumentSnapshot.isEmpty()).willReturn(false);
        given(mockedQueryDocumentSnapshot.get(anyInt())).willReturn(mockedQueryDocumentSnapShot1);
        given(mockedQueryDocumentSnapShot1.getReference()).willReturn(mockedDocumentReference);
        given(mockedDocumentReference.set(any(ContactEntity.class))).willReturn(null);
        testable.updateContact(contact);
        verify(dbFireStore, times(1)).collection(anyString());
        verify(mockedDocumentReference, atLeastOnce()).set(any(ContactEntity.class));
    }

    @Mock
    List<ContactEntity> contacts ;

    @Test
    void whenAddListOfContactsButThisListIsEmpty () {

        MockedStatic mockedClient = Mockito.mockStatic(FirestoreClient.class);
        mockedClient.when(FirestoreClient::getFirestore).thenReturn(dbFireStore);
        given(contacts.isEmpty()).willReturn(true);
        assertThrows(ApiRequestException.class, () -> testable.addListContacts(contacts));

    }

    @Test
    void whenAddListOfContactsButThisListIsNull() {
         contacts=null;
        MockedStatic mockedClient = Mockito.mockStatic(FirestoreClient.class);
        mockedClient.when(FirestoreClient::getFirestore).thenReturn(dbFireStore);
        assertThrows(ApiRequestException.class, () -> testable.addListContacts(contacts));

    }
    @Test
    void whenAddListOfContactsAndThisListHaveMinimumOneObject() {
        contacts.add(contact);
        MockedStatic mockedClient = Mockito.mockStatic(FirestoreClient.class);
        mockedClient.when(FirestoreClient::getFirestore).thenReturn(dbFireStore);
        given(dbFireStore.collection(anyString())).willReturn(mockedCollection);
        given(contacts.size()).willReturn(1);
        testable.addListContacts(contacts);
        verify(dbFireStore,atLeastOnce()).collection(anyString());
    }

}

