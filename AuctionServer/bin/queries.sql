DELIMITER //
CREATE PROCEDURE country_hos
(IN con CHAR(20))
BEGIN
  SELECT Name, HeadOfState FROM Country
  WHERE Continent = con;
END //
DELIMITER ;
 
CREATE PROCEDURE insert_user
(IN Id1 mediumint(8),
  First_Name1 varchar(255),
  Surname1 varchar(255),
  Password1 TEXT,
  Email1 varchar(255),
  Credit1 mediumint(8)
  )
BEGIN
    INSERT INTO users (id,First_Name,Surname,Password,Email) VALUES (Id1, First_Name1, Surname1, Password1, Email1, Credit1);
END

CREATE PROCEDURE insert_leilao
(IN Id1 mediumint(8),
	Highest_bidder1 mediumint(8),
	Owner1 mediumint(8),
	Highest_bid1 mediumint(8),
	End_date1 datetime,
	Item_id1 mediumint(8)
)
BEGIN
    INSERT INTO leiloes(id, Highest_bidder, Owner, Highest_bid, End_date, Item_id) VALUES(Id1, Highest_bidder1, Owner1, Highest_bid1, End_date1, Item_id1);
END

CREATE PROCEDURE get_user_by_id
(IN user_id mediumint(8))
BEGIN
    select * from users
    where Id = user_id;
END


CREATE PROCEDURE listar_leiloes
BEGIN
    select * from leiloes;
END


CREATE PROCEDURE insert_bid
(IN Id_user mediumint(8),
    Id_leilao mediumint(8),
    new_bid mediumint(8),
)
BEGIN
    
END
