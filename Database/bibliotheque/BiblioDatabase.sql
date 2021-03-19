CREATE DATABASE Biblio;
USE Biblio;
DROP TABLE IF EXISTS client;
CREATE TABLE kunde (
kunde_ID INT  UNSIGNED NOT  NULL  AUTO_INCREMENT PRIMARY KEY,
vorname VARCHAR(60) NOT NULL ,
nachname VARCHAR(60) NOT NULL,
email VARCHAR(100) ,
phone VARCHAR (15)
)   ;
INSERT INTO Kunde (vorname,nachname, email,phone) 
	VALUES  ('ahmed','mohamed','ahmed@gmail.com','0172354567'),
            ('peter','hoffman','peter@gmail.com','01959997967'),
			('omar','mohamed','omar@gmail.com','0162656854857'),
            ('alex','seeman','alexQgmail.com',01245654644),
            ('Thomas','Hann','bestmann@gmail.com',01245635645644);
SELECT * FROM kunde;

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
foreign key(typ_id)references kunde (kunde_id)
);

insert into kunde_typ(typ_id,sorte)
values(2,'stuent');

 Drop table if  exists Buch ;
 create table Buch(
 buch_id INT  UNSIGNED NOT  NULL  AUTO_INCREMENT PRIMARY KEY, 
 exampler int unsigned default 1, 
 buchName varchar (250) not null,
 kunde_ID INT  UNSIGNED NOT  NULL,
 foreign key (kunde_id)references kunde (kunde_id),
 unique(Buch_id) );
 insert into Buch( exampler,buchName,Kunde_id)
 values (1, 'das Land',3),
        (5, 'das wetter vor 100 jahre',1),
        (10, 'journey to the center of the earth',4);
 

 drop table if exists Autor;
 create table Autor (
 VorName  varchar (100) not null, 
 nachNAme  varchar (100) ,
 autor_id int unsigned not null primary key 
 );
 insert into Autor(vorname,nachname,autor_id)
 values('isabel','Abedi',1215),
      	('jan','Beck',1211),
        ('Wolf','Hass',1205);
 
 
 
 
 
 
 
 
 drop table if exists verfasst;
 create table verfasst( 
 buch_id INT  UNSIGNED NOT  NULL , 
  autor_id int unsigned not null ,
  foreign key (buch_id)references Buch (buch_id) on delete cascade on update cascade,
  foreign key (autor_id)references autor (autor_id) on delete cascade on update cascade);

  insert into verfasst(buch_id,autor_id)
  values (1,1211),
         (3,1205),
         (2,1215);
 drop table if exists buch_ausliehen;
 create table buch_ausliehen (
 kunde_id  INT  UNSIGNED NOT  NULL,
 buch_id int unsigned not null,
 von_datum date  not null,
 bis_datum date not null);
 insert into buch_aus (kunde_id,buch_id,von_datum,bis_datum)
 values (3,1205,01052020,6h);
 