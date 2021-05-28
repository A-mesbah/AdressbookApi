package de.inmediasp.adressBook;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdressBookApplication {
    private static Logger logger = LogManager.getLogger(AdressBookApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AdressBookApplication.class, args);
        System.out.println("hallo before logger ");
        logger.info("this is information message ");
        logger.error("this is Error message ");
        System.out.println("hallo after logger");
    }

}
