<?php
$hostname = "test.db.some#.somehost.com";
$username = "test";
$dbname = "test";
$password = "password";
$usertable = "test";
$yourfield = "UN";

$connection = mysql_connect($hostname, $username, $password) OR DIE ("Unable to connect to database! Please try again later.");
mysql_select_db($dbname);
$query = "SELECT * FROM $usertable WHERE $yourfield = '".$_GET["U"]."'";
$result = mysql_query($query);
    if ($result) {
        while($row = mysql_fetch_array($result)) {
                $name = $row["$yourfield"];
                echo "Hello: $name<br>";
        }
    }
    else {
        echo "User dosen't exit!";
        }
    mysql_close($connection);
?>
