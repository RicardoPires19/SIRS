DROP DATABASE leiloes_sirs;

create database leiloes_sirs;

use leiloes_sirs;


CREATE TABLE users (
  Id mediumint(8) unsigned NOT NULL auto_increment,
  First_Name varchar(255) default NULL,
  Surname varchar(255) default NULL,
  Password TEXT default NULL,
    Salt TEXT default NULL,
  Email varchar(255) default NULL,
  Credit mediumint(8) default 10000,
  PRIMARY KEY (Id)
) AUTO_INCREMENT=1;

/*
CREATE TABLE items (
	Id mediumint(8) unsigned NOT NULL auto_increment,
	Name varchar(255) default NULL,
	PRIMARY KEY (Id)
) AUTO_INCREMENT=1;
*/

CREATE TABLE leiloes (
	Id mediumint(8) unsigned NOT NULL auto_increment,
	Highest_bidder mediumint(8) unsigned NOT NULL default Id,
	Owner mediumint(8) unsigned NOT NULL,
	Highest_bid int(8) NOT NULL default 0,
	End_date datetime  default NULL,
    ItemDescription TEXT default NULL,
	/*Item_id mediumint(8) unsigned default NULL,*/
	PRIMARY KEY (Id),
	FOREIGN KEY (Owner) REFERENCES users(Id),
	FOREIGN KEY (Highest_bidder) REFERENCES users(Id)
	/*FOREIGN KEY (Item_id) REFERENCES items(Id)*/
) AUTO_INCREMENT=1;


/*
INSERT INTO items (Id, Name) VALUES (1, "Carro");
INSERT INTO items (Id, Name) VALUES (2, "Casa");
INSERT INTO items (Id, Name) VALUES (3, "Apartamento");
INSERT INTO items (Id, Name) VALUES (4, "Mota");
INSERT INTO items (Id, Name) VALUES (5, "Computador");
INSERT INTO items (Id, Name) VALUES (6, "Barco");*/


INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1000,"Xena","Mathis","id","lectus.rutrum@DonecnibhQuisque.com");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1001,"Lewis","Lynch","purus,","Proin@purusaccumsaninterdum.net");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1002,"Fallon","Gibbs","sit","venenatis.a.magna@uterat.com");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1003,"Penelope","Decker","at","massa.Integer.vitae@dictumeueleifend.net");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1004,"Yoshi","Hurley","Morbi","ac@utlacus.ca");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1005,"Otto","Fuller","Aliquam","auctor.Mauris@mollis.com");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1006,"Preston","Byers","molestie","sed.tortor.Integer@sit.ca");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1007,"Justin","Nicholson","nulla.","netus.et.malesuada@nonummyacfeugiat.co.uk");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1008,"Uma","Gallegos","nunc","velit@necanteMaecenas.com");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1009,"Lael","Martin","ornare.","Etiam@vitaeorciPhasellus.org");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1010,"Vaughan","Hewitt","Duis","eleifend@dolor.com");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1011,"Imogene","Owen","scelerisque","natoque.penatibus@viverraDonec.net");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1012,"Merrill","Garrett","sapien","felis.orci.adipiscing@etipsum.ca");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1013,"Shelly","Mcfarland","tellus","magna@est.net");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1014,"Hiram","Haney","ornare","lacinia@Donecfelisorci.org");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1015,"Abdul","Burt","lorem.","velit.eu.sem@estarcu.com");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1016,"Hanae","Garrett","nunc","pede.et.risus@tristique.org");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1017,"Dacey","Powers","Nullam","dui@Aliquam.net");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1018,"Elvis","Elliott","Sed","rhoncus.Proin@Vivamus.ca");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1019,"Serena","Kane","non,","turpis.egestas.Aliquam@penatibusetmagnis.co.uk");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1020,"Eric","Conrad","ultrices","sem.eget@Vestibulum.net");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1021,"Kitra","Morse","tempor","elementum@asollicitudinorci.co.uk");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1022,"Dai","Heath","Duis","pede.Suspendisse@condimentumDonec.ca");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1023,"Dylan","Becker","id","dui.Fusce.aliquam@arcuacorci.edu");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1024,"Bryar","Valentine","rutrum","erat@amifringilla.co.uk");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1025,"Jana","Little","Proin","ligula.consectetuer.rhoncus@nibhQuisque.com");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1026,"Cecilia","Lloyd","nulla","sociis@mattisInteger.edu");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1027,"Hyacinth","Hebert","odio","nec.orci@egetnisi.com");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1028,"Madison","Humphrey","non","placerat.Cras.dictum@ligulaAeneaneuismod.com");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1029,"Joan","Harmon","venenatis","lorem.auctor.quis@Duisac.com");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1030,"Dorian","Mitchell","urna.","hendrerit.Donec@adipiscingnonluctus.com");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1031,"Derek","Peck","vehicula","nec.tellus.Nunc@iaculis.ca");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1032,"Silas","Mueller","Duis","semper.rutrum.Fusce@augue.ca");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1033,"Orlando","Morse","amet","sollicitudin.orci.sem@erat.org");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1034,"Constance","Hammond","accumsan","tristique.ac.eleifend@Nuncuterat.co.uk");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1035,"Libby","Bernard","aliquam","amet.risus@auguescelerisque.org");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1036,"Lewis","Stevenson","In","ipsum.dolor@ametconsectetuer.ca");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1037,"Sylvester","Workman","molestie","convallis@odioa.net");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1038,"Brian","Browning","Nunc","ac.facilisis@hendrerit.edu");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1039,"Tarik","Mcdowell","semper","Cum.sociis.natoque@consectetuer.edu");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1040,"Halee","Brady","sagittis","tempor@Craseutellus.co.uk");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1041,"Colorado","Gutierrez","nostra,","Lorem.ipsum.dolor@Phasellus.ca");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1042,"Neve","Lewis","vel","Phasellus.dolor@eratsemper.ca");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1043,"Daria","Cleveland","commodo","Aliquam.rutrum.lorem@commodo.ca");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1044,"Ursula","James","dolor","Nulla.interdum@dis.co.uk");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1045,"Jennifer","Durham","Lorem","lobortis@elit.edu");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1046,"Norman","Phelps","vestibulum","pellentesque@est.net");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1047,"Urielle","Paul","semper","nunc.In@nec.com");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1048,"Tad","Mcfadden","ornare","risus.quis@malesuadafringillaest.org");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1049,"Brenna","Bauer","aliquam","amet.ornare.lectus@Integervulputate.co.uk");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1050,"Mariko","Albert","sit","ac@rutrumloremac.co.uk");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1051,"Bryar","Potts","nec","vitae@infaucibusorci.edu");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1052,"Larissa","Horne","sed","facilisi.Sed.neque@dictum.com");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1053,"Timothy","Burks","ante","eget@ultricessit.ca");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1054,"Urielle","Holloway","et","a.tortor@Inornaresagittis.co.uk");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1055,"Lavinia","Macdonald","Vestibulum","dictum.cursus.Nunc@nuncac.net");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1056,"Gil","Clements","gravida","ligula.Aenean@egetipsumDonec.edu");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1057,"Lois","Foreman","interdum","orci@dapibus.org");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1058,"Briar","Moody","Etiam","iaculis.quis.pede@magnaDuis.co.uk");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1059,"Miriam","Bolton","purus.","dictum.augue.malesuada@inconsectetueripsum.edu");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1060,"Donna","Guthrie","accumsan","dictum.sapien@Phasellusat.co.uk");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1061,"Clio","Morales","Nam","aliquam.enim.nec@venenatislacusEtiam.net");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1062,"Daquan","Reese","Phasellus","Ut.tincidunt.vehicula@inmolestie.net");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1063,"Madeson","Molina","malesuada","neque.et@lacusCras.net");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1064,"Vaughan","Ware","Phasellus","Lorem@posuere.ca");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1065,"Donovan","Joseph","scelerisque","non.enim.commodo@enimSuspendisse.ca");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1066,"Vernon","Simpson","aliquet","semper.auctor.Mauris@enim.edu");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1067,"Elaine","Levy","tincidunt","sem.Nulla@penatibus.org");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1068,"Karleigh","George","dapibus","sit@quamCurabiturvel.ca");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1069,"Amos","Underwood","Vivamus","accumsan@etlacinia.edu");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1070,"Fitzgerald","Morgan","fringilla","lobortis.quam.a@convalliserat.com");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1071,"Whoopi","Giles","lacus","eget.massa.Suspendisse@nonmagnaNam.net");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1072,"Melanie","Mcpherson","non,","euismod.in@magnaLorem.edu");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1073,"Nyssa","Howe","Nulla","enim@ornare.co.uk");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1074,"Naida","Hewitt","Proin","cursus@lacusMaurisnon.edu");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1075,"Lacota","Farley","vitae,","egestas.Duis.ac@Suspendissetristique.net");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1076,"Zelenia","Berry","et","vitae.purus.gravida@magnaCrasconvallis.org");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1077,"Tara","Cox","condimentum.","dolor.sit.amet@posuere.ca");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1078,"Kai","Wagner","enim","arcu.imperdiet.ullamcorper@aliquet.net");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1079,"Abdul","Hughes","rutrum","eget.metus.In@necante.org");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1080,"Yen","Burton","sit","felis.orci.adipiscing@enimCurabitur.org");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1081,"Jescie","Luna","faucibus","Morbi.accumsan.laoreet@Nullam.co.uk");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1082,"Kimberly","Monroe","arcu.","eros.turpis@disparturient.co.uk");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1083,"Armand","Pena","turpis.","tincidunt@nonhendreritid.ca");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1084,"Tamekah","Mendez","Pellentesque","libero.est.congue@orciluctuset.com");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1085,"Carly","Carey","ante","lectus.quis.massa@Nam.com");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1086,"Justine","Frank","malesuada","ultrices@egetmetuseu.org");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1087,"Cairo","Joyner","dolor,","lorem.ac@vestibulumMauris.ca");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1088,"Alan","Hebert","In","orci@atpede.ca");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1089,"Virginia","Cantu","sapien.","nec.mollis@ametanteVivamus.ca");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1090,"Jeanette","Marshall","nisi.","sapien.molestie@cursuset.net");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1091,"Harper","Ford","pharetra","est@sedhendrerita.edu");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1092,"Angela","Case","dui","mollis@acarcuNunc.com");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1093,"Rae","Holden","sagittis","posuere.at@montesnascetur.ca");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1094,"Larissa","Monroe","sit","nec@ac.ca");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1095,"Carol","Morrison","augue","mauris.ut.mi@Pellentesque.co.uk");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1096,"Ivan","Wells","ut,","non@sapiencursus.co.uk");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1097,"Brent","Hebert","vel,","turpis@ac.net");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1098,"Travis","Cox","Nunc","nec@estNunc.org");
INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (1099,"Laurel","Tyson","amet,","erat.in.consectetuer@nonbibendum.co.uk");
