package de.inmediasp.adressBook.repository;

import de.inmediasp.adressBook.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepo extends JpaRepository<Contact,Long>   {

// findByFNameIgnoreCase -->this an another function may i use later
//public List<Contact> findByFName (String fristName );
/*// Their is a another  method  to use your Qurey like the MySQL QUery(use the name of table and attribute according to your calss not like the name in Db(@Column(name="blabla")
    @Query("select * from contacts Contact ")
    public List <Contact> allContacts();
    @Query("select con from contacts con  Contact where con.fName=:fristNameBind ")
    public List <Contact> SearchContacts(@Param("fristNameBind")String fristName );*/
}
