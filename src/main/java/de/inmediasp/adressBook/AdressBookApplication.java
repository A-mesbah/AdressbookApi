package de.inmediasp.adressBook;

import com.google.api.core.ApiFuture;
import com.google.api.gax.rpc.ApiStreamObserver;
import com.google.cloud.firestore.*;
import de.inmediasp.adressBook.model.ContactDTO;
import de.inmediasp.adressBook.model.ContactEntity;
import de.inmediasp.adressBook.repository.IFBContactRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
@SpringBootApplication
public class AdressBookApplication {
    private static Logger logger = LogManager.getLogger(AdressBookApplication.class);

    @Bean
    public ModelMapper modelmapper() {
        return new ModelMapper();
    }

    @Bean
    public Firestore firestore(){
        return new Firestore() {
            @Nonnull
            @Override
            public CollectionReference collection(@Nonnull String s) {
                return null;
            }

            @Nonnull
            @Override
            public DocumentReference document(@Nonnull String s) {
                return null;
            }

            @Nonnull
            @Override
            public Iterable<CollectionReference> listCollections() {
                return null;
            }

            @Nonnull
            @Override
            public Iterable<CollectionReference> getCollections() {
                return null;
            }

            @Override
            public Query collectionGroup(@Nonnull String s) {
                return null;
            }

            @Nonnull
            @Override
            public <T> ApiFuture<T> runTransaction(@Nonnull Transaction.Function<T> function) {
                return null;
            }

            @Nonnull
            @Override
            public <T> ApiFuture<T> runTransaction(@Nonnull Transaction.Function<T> function, @Nonnull TransactionOptions transactionOptions) {
                return null;
            }

            @Nonnull
            @Override
            public ApiFuture<List<DocumentSnapshot>> getAll(@Nonnull DocumentReference... documentReferences) {
                return null;
            }

            @Nonnull
            @Override
            public ApiFuture<List<DocumentSnapshot>> getAll(@Nonnull DocumentReference[] documentReferences, @Nullable FieldMask fieldMask) {
                return null;
            }

            @Override
            public void getAll(@Nonnull DocumentReference[] documentReferences, @Nullable FieldMask fieldMask, ApiStreamObserver<DocumentSnapshot> apiStreamObserver) {

            }

            @Nonnull
            @Override
            public WriteBatch batch() {
                return null;
            }

            @Override
            public void close() throws Exception {

            }

            @Override
            public FirestoreOptions getOptions() {
                return null;
            }
        };
    }


    public static void main(String[] args) {
        SpringApplication.run(AdressBookApplication.class, args);
        System.out.println("hallo before logger ");
        logger.info("this is information message ");
        logger.error("this is Error message ");
        System.out.println("hallo after logger");
    }

}
