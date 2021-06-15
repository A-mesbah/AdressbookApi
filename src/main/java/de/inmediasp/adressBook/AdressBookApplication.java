package de.inmediasp.adressBook;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdressBookApplication {
    private static Logger logger = LogManager.getLogger(AdressBookApplication.class);

    @Bean
    public ModelMapper modelmapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(AdressBookApplication.class, args);
        System.out.println("hallo before logger ");
        logger.info("this is information message ");
        logger.error("this is Error message ");
        System.out.println("hallo after logger");
    }

}
