#Zeige Anzahl der Studenten, Senioren, normalen Nutzer
select count(*)from mensch;

# Zeige alle Kunden, deren Vorname mit K beginnt
select m.* from mensch m where m.vorname like'k%';

# Zeige alle Bibliothekare, deren Postleitzahl mit  beginnt 25
select m.*,ma.Ad_plz from mensch m
join mensh_adresse ma on m.id=ma.mensch_id
join bibliothekar b on b.mensch_id= ma.mensch_id  where ma.Ad_Plz like '25%';


# Zeige Anzahl aller Senioren, die momentan ein Buch ausgeliehen haben
select count(*) from kunde where kunde.sorte ='Senior'; 
 
# Zeige alle Studenten, die unbezahlte Mahngebühren haben
select k.*,g.beitrag,g.is_bezahlt from kunde k 
join Gebuhren g on k.kunde_id =g.kunde_id 
where k.sorte ='Student' and g.is_bezahlt=0;

# Zeige alle Autoren, von deren Büchern Exemplare im Jahr 2000 ausgeliehen wurden
select a.*,ex.* from Autor a 
join verfasst v on a.autor_id=v.autor_id
join Buch b on v.buch_id =b.buch_id
join exemplar ex where year(ex.erscheingsJahr)=2000  and b.buch_id =ex.buch_id ;

#Zeige alle normalen Bibliotheksnutzer, die offene Gebühren haben
select M.* ,k.sorte,g.gebuhren_id,g.beitrag,g.is_bezahlt from Mensch m 
join kunde k on m.id=k.kunde_id 
join gebuhren g on  k.kunde_id =g.kunde_id where k.sorte='Normal' and g.is_bezahlt ='0';
 
 #Zeige die Summe der Einnahmen der Bibliothek geordnet nach Art der Gebühr
select sum(g.beitrag) as Mahnung from gebuhren g where g.is_bezahlt =1 and g.beitrag<=60;
select sum(g.beitrag) as strafe  from gebuhren g where g.is_bezahlt =1 and g.beitrag>60;
select sum(g.beitrag) as TotalGebuhren from gebuhren g where g.is_bezahlt =1 ;

# Zeige alle verfügbaren Exemplare aller Bücher vom Autor'perry Davis'
select a.*,ex.* from autor a
join verfasst v on a.autor_id= v.autor_id 
join exemplar ex on  v.buch_id= ex.buch_id  
where a.VorName='perry' and a.nachName='Davis';

# zeige historisiert an welche Personen ein bestimmtes Buch ausgeliehen haben

select m.*,k.sorte ,b.*,ba.von_datum,ba.bis_datum from mensch m 
join  kunde k on m.id= k.kunde_id
join buch_ausliehen ba on ba.kunde_id= k.kunde_id 
join  buch b on b.buch_id =ba.buch_id where b.buchName='labore' and ba.von_datum='2020-06-06';

# Erstelle Indizes für deine Tabelle und überprüfe ob sich die Abfragebeschwindigkeit erhöht
CREATE INDEX mensch_index  ON mensch (id);
select* from mensch where nachname like '%a%';