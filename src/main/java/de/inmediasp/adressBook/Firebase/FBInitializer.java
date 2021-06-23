package de.inmediasp.adressBook.Firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


@Service
public class FBInitializer {
    @PostConstruct
    public void initialize() {
        try {

            FileInputStream serviceAccount =
                    new FileInputStream("C:\\Users\\amesbah\\javaProjects\\Bitbuckt_inmediasp\\alltryouts\\adressBook\\src\\main\\resources\\adressbooKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

