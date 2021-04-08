create database biblio1;
use biblio1;
CREATE TABLE  IF not  EXISTS kunde (
kunde_ID INT  UNSIGNED NOT  NULL  AUTO_INCREMENT PRIMARY KEY,
vorname VARCHAR(60) NOT NULL ,
nachname VARCHAR(60) NOT NULL,
sorte VARCHAR (50)not null,
email VARCHAR(100) ,
phone VARCHAR (15),
strasse VARCHAR( 100) NOT NULL, 
hausnummer INT UNSIGNED NOT NULL,
plz INT UNSIGNED NOT NULL ,
stadt VARCHAR (100) NOT NULL 
)   ;

 CREATE TABLE  IF not  EXISTS Buch(
 buch_id INT  UNSIGNED NOT  NULL   PRIMARY KEY,  
 buchName VARCHAR (250) NOT NULL)
 ;
 
  CREATE TABLE  IF not  EXISTS  Exampler (
 exmpler_id INT  UNSIGNED NOT  NULL auto_increment  PRIMARY KEY, 
 buch_id INT  UNSIGNED NOT  NULL   , 
 exampler_zahl int unsigned default 1,
 exampler_JAHR date   not null, 
 foreign key (buch_id) references Buch(buch_id) ON DELETE cascade ON UPDATE CASCADE
 );
Drop table buch_ausliehen;
  CREATE TABLE  IF not  EXISTS buch_ausliehen (
 kunde_id  INT  UNSIGNED  ,
 buch_id INT UNSIGNED NOT NULL,
 exmpler_id INT  UNSIGNED NOT  NULL , 
 von_datum DATE  NOT NULL,
 bis_datum DATE NOT NULL,
 strafArt  boolean  as (bis_datum-von_datum<=10),
 FOREIGN KEY(Kunde_id)REFERENCES kunde (kunde_id),
 FOREIGN KEY(buch_id)REFERENCES Buch (buch_id) 
 );
 
 CREATE TABLE  IF not  EXISTS Gebuhren (
 gebuhren_id int unsigned not null primary key,
 kunde_id int unsigned not null,
 buch_id INT UNSIGNED NOT NULL,
 exmpler_id int unsigned ,
 beitrag INT UNSIGNED ,
 is_bezahlt boolean,
 FOREIGN KEY(Kunde_id)REFERENCES buch_ausliehen (kunde_id) on update cascade   on delete Cascade,
 FOREIGN KEY(buch_id)REFERENCES buch_ausliehen (buch_id) on update cascade   on delete cascade,
FOREIGN KEY(exmpler_id)REFERENCES Exampler(exmpler_id) 
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
 bib_id INT  UNSIGNED NOT  NULL PRIMARY KEY,
 vorName VARCHAR(50) NOT NULL,
 nachName VARCHAR(50) NOT NULL,
 email VARCHAR(100)NOT NULL,
 phone VARCHAR(50) NOT NULL,
 Bib_Ad_Str VARCHAR (50) NOT NULL,
 Bib_Ad_haus_nr int unsigned  NOT NULL,
 Bib_Ad_Plz INT UNSIGNED  NOT NULL,
 Bib_Ad_std VARCHAR (100) NOT NULL
 );
 create table if not exists verwaltet(
 bib_id INT  UNSIGNED ,
 kunde_ID INT  UNSIGNED,
 FOREIGN KEY (bib_id)REFERENCES Bibliothekar(bib_id) on delete set null on update cascade,
 FOREIGN KEY ( kunde_ID)REFERENCES kunde (kunde_id) on delete cascade  on update cascade
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