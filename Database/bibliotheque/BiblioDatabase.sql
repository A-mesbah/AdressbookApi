CREATE DATABASE Biblio;
USE Biblio;
DROP TABLE IF EXISTS Kunde ;
CREATE TABLE kunde (
kunde_ID INT  UNSIGNED NOT  NULL  AUTO_INCREMENT PRIMARY KEY,
vorname VARCHAR(60) NOT NULL ,
nachname VARCHAR(60) NOT NULL,
email VARCHAR(100) ,
phone VARCHAR (15)
)   ;
INSERT INTO Kunde (vorname,nachname, email,phone) 
	VALUES ('ahmed','mohamed','ahmed@gmail.com','0172354567'),
            ('peter','hoffman','peter@gmail.com','01959997967'),
			('omar','mohamed','omar@gmail.com','0162656854857'),
            ('alex','seeman','alexQgmail.com',01245654644),
            ('Thomas','Hann','bestmann@gmail.com',01245635645644);
            

DROP TABLE IF EXISTS kunde_ADRESS;
CREATE TABLE kunde_Adress(
kunde_ID INT  UNSIGNED NOT  NULL  AUTO_INCREMENT PRIMARY KEY,
strasse VARCHAR( 100) NOT NULL, 
hausnummer INT UNSIGNED NOT NULL,
plz INT UNSIGNED NOT NULL ,
stadt VARCHAR (100) NOT NULL ,
UNIQUE(kunde_id),
FOREIGN KEY(kunde_id) REFERENCES kunde(kunde_id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO Kunde_adress (strasse,hausnummer, plz,stadt) 
	VALUES  ('BerlinerStr',15,16540,'Hennigsdorf'),
            ('GotheStr',14,12540,'berlin'),
            ('BremerStr',17,18055,'Rostock'),
             ('BerlinerStr',15,16540,'Hennigsdorf'),
             ('BudabesterStr',112,4126,'Essen');

SELECT k.kunde_id,k.vorname ,k.nachname,k.email,k.phone ,ka.strasse ,ka.hausnummer ,ka.plz,ka. stadt 
FROM kunde k  JOIN kunde_adress ka ON  k.kunde_id=ka.kunde_id;



DROP TABLE IF EXISTS kunde_typ;
CREATE TABLE kunde_typ(
typ_id INT UNSIGNED NOT NULL ,
sorte VARCHAR (50),
FOREIGN KEY(typ_id)REFERENCES kunde (kunde_id)
);

INSERT INTO kunde_typ(typ_id,sorte)
VALUES(2,'stuent'),
     (3,'senior'),
     (4,'senior');
INSERT INTO kunde_typ(typ_id,sorte)
VALUES(4,'senior');
 DROP TABLE IF  EXISTS Buch ;
 CREATE TABLE Buch(
 buch_id INT  UNSIGNED NOT  NULL  AUTO_INCREMENT PRIMARY KEY, 
 exampler INT UNSIGNED DEFAULT 1, 
 buchName VARCHAR (250) NOT NULL,
 kunde_ID INT  UNSIGNED NOT  NULL,
 FOREIGN KEY (kunde_id)REFERENCES kunde (kunde_id),
 UNIQUE(Buch_id) );
 INSERT INTO Buch( exampler,buchName,Kunde_id)
 VALUES (1, 'das Land',3),
        (5, 'das wetter vor 100 jahre',1),
        (10, 'journey to the center of the earth',4);
 

 DROP TABLE IF EXISTS Autor;
 CREATE TABLE Autor (
 VorName  VARCHAR (100) NOT NULL, 
 nachNAme  VARCHAR (100) ,
 autor_id INT UNSIGNED NOT NULL PRIMARY KEY 
 );
 INSERT INTO Autor(vorname,nachname,autor_id)
 VALUES('isabel','Abedi',1215),
      	('jan','Beck',1211),
        ('Wolf','Hass',1205);
 
 
 
 
 
 
 
 
 DROP TABLE IF EXISTS verfasst;
 CREATE TABLE verfasst( 
 buch_id INT  UNSIGNED NOT  NULL , 
  autor_id INT UNSIGNED NOT NULL ,
  FOREIGN KEY (buch_id)REFERENCES Buch (buch_id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (autor_id)REFERENCES autor (autor_id) ON DELETE CASCADE ON UPDATE CASCADE);

  INSERT INTO verfasst(buch_id,autor_id)
  VALUES (1,1211),
         (3,1205),
         (2,1215);
 DROP TABLE IF EXISTS buch_ausliehen;
 CREATE TABLE buch_ausliehen (
 kunde_id  INT  UNSIGNED NOT  NULL,
 buch_id INT UNSIGNED NOT NULL,
 von_datum DATE  NOT NULL,
 bis_datum DATE NOT NULL);
 INSERT INTO buch_ausliehen (kunde_id,buch_id,von_datum,bis_datum)
 VALUES (3,1205,'2020-05-11','2020-05-22'),
         (1,1215,'2020-03-02','2020-03-07'),
         (2,1211,'2020-06-01','2020-08-20');
 DROP TABLE IF EXISTS Bibliothekar;
 CREATE TABLE  Bibliothekar(
 bib_id INT  UNSIGNED NOT  NULL AUTO_INCREMENT PRIMARY KEY,
 vorName VARCHAR(50) NOT NULL,
 nachName VARCHAR(50) NOT NULL,
 email VARCHAR(100)NOT NULL,
 phone VARCHAR(50) NOT NULL,
 Bib_Ad_Str VARCHAR (50) NOT NULL,
 Bib_Ad_haus_nr VARCHAR (7) NOT NULL,
 Bib_Ad_Plz INT UNSIGNED  NOT NULL,
 Bib_Ad_std VARCHAR (100) NOT NULL,
 UNIQUE(bib_id)
 );

 INSERT INTO Bibliothekar (vorname,nachname ,email,phone,Bib_Ad_Str,Bib_Ad_haus_nr,Bib_Ad_Plz,Bib_Ad_std)
 VALUES
     ('Oliver ' ,'Herrman','oliver@123com',0123456789,'hamburerStr' ,'13a'    ,50254, 'Ulm'),
      ('stefan ','jank'   ,'stefen@123com',0132965478,'burerStr'    ,'5'       ,51254,'Köln'      ),
       ('murad','ibra'    ,'ibra@123com'  ,0114572357,'alexanderStr','1'       ,50255,'gISSEN'    );
 
 