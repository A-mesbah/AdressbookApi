create database biblio1;
use biblio1; 


 create table if not exists Mensch(
 id INT  UNSIGNED NOT  NULL  AUTO_INCREMENT PRIMARY KEY,
vorname VARCHAR(60) NOT NULL ,
nachname VARCHAR(60) NOT NULL,
email VARCHAR(100) ,
phone VARCHAR (15),
unique(email,phone)
);
CREATE TABLE  IF not  EXISTS Kunde (
kunde_id Int unsigned not null ,
sorte enum ('student','Senior', 'Normal') not null,
 foreign key (kunde_id) references Mensch (id) ON DELETE cascade ON UPDATE CASCADE
);

create table if not exists Mensh_adresse(
mensch_id  INT  UNSIGNED NOT  NULL,
Ad_Str VARCHAR (50) NOT NULL,
 Ad_haus_nr int unsigned  NOT NULL,
 Ad_Plz INT UNSIGNED  NOT NULL,
 Ad_std VARCHAR (100) NOT NULL,

foreign key (mensch_id ) references Mensch (id) ON DELETE cascade ON UPDATE CASCADE
);

 CREATE TABLE  IF not  EXISTS Buch(
 buch_id INT  UNSIGNED NOT  NULL   PRIMARY KEY,  
 buchName VARCHAR (250) NOT NULL)
 ;
 #rename table exampler to Exemplar ;
  CREATE TABLE  IF not  EXISTS  Exemplar (
 exemplar_id INT  UNSIGNED NOT  NULL auto_increment  PRIMARY KEY, 
 buch_id INT  UNSIGNED NOT  NULL    , 
 edition int unsigned default 1,
erscheingsJahr date   not null, 
 foreign key (buch_id) references Buch(buch_id) ON DELETE cascade ON UPDATE CASCADE
 );
#  alter table Exemplar rename column  exampler_zahl to edition ;
 #alter table Exemplar rename column  exampler_jahr to erscheingsJahr ;
 

CREATE TABLE  IF not  EXISTS buch_ausliehen (
 kunde_id  INT  UNSIGNED  ,
 buch_id INT UNSIGNED NOT NULL,
 exemplar_id INT  UNSIGNED NOT  NULL , 
 von_datum DATE  NOT NULL,
 bis_datum DATE NOT NULL,
 strafArt  boolean  as (bis_datum-von_datum<=10),
 FOREIGN KEY(kunde_id)REFERENCES Mensch(id),
 FOREIGN KEY(buch_id)REFERENCES Buch (buch_id) 
 );
 
 CREATE TABLE  IF not  EXISTS Gebuhren (
 gebuhren_id int unsigned not null primary key,
 kunde_id int unsigned not null,
 exemplar_id int unsigned ,
 beitrag INT UNSIGNED ,
 is_bezahlt boolean,
 FOREIGN KEY(kunde_id)REFERENCES Mensch(id) ,
FOREIGN KEY(exemplar_id)REFERENCES Exemplar(exemplar_id) 
);

  CREATE TABLE  IF not  EXISTS verfasst( 
 buch_id INT  UNSIGNED NOT  NULL UNIQUE , 
  autor_id INT UNSIGNED  ,
  FOREIGN KEY (buch_id)REFERENCES Buch (buch_id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (autor_id)REFERENCES Autor (autor_id) ON DELETE set null ON UPDATE CASCADE)
;

 CREATE TABLE  IF not  EXISTS Autor (
 VorName  VARCHAR (100) NOT NULL, 
 nachName  VARCHAR (100) not null,
 autor_id INT UNSIGNED NOT NULL PRIMARY KEY 
 );
 
 CREATE TABLE  IF not  EXISTS Bibliothekar(
 mensch_id INT  UNSIGNED NOT null ,
 bib_id INT  UNSIGNED NOT  NULL PRIMARY KEY,
 FOREIGN KEY (mensch_id)REFERENCES Mensch(id) 
 );
 create table if not exists verwaltet(
 bib_id INT  UNSIGNED ,
 kunde_id INT  UNSIGNED,
 FOREIGN KEY (bib_id)REFERENCES Bibliothekar(bib_id) ,
 FOREIGN KEY ( kunde_id)REFERENCES Mensch(id) 
 );
 
  drop function  if exists istüberschritten;
 DELIMITER $$
 create function    istuberschritten  (  von_datum Date   ,bis_datum Date  )
 returns  varchar (50)
DETERMINISTIC
 begin
 DECLARE Lage  VARCHAR(20);
 IF( bis_datum-von_datum<= 10 )THEN
		SET lage= 'keine_strafe ';
        elseif (bis_datum-von_datum> 15)then 
        set lage ='Strafe';
        elseif (bis_datum-von_datum>10 and bis_datum-von_datum<=15)then 
 set lage ='Mahnung ';
 end if ;
 return (Lage);
 end $$
  DELIMITER ;
  
  
  select count(*) from kunde ;
  select k.vorname  from Kund k where vorname like 'k%';
  select b.* from bibliothekar b where plz like '50%';
  select count (sorte)  from kunde k ,buch_ausliehen b_a where k.sorte="senior" and b_a.buch_id= null;
  select k.*  from kunde k where k.sorte="student" join Gebuhren on 