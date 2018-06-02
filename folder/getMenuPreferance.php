<?php
	$db = mysqli_connect('localhost','root','','IDS')
	or die('Error connecting to MySQL server.');
	$id = 0;
	$username=$_POST['username'];
	$password=$_POST['password'];
	$query1 = "start transaction;";
	$query2 = "select @idNo:=max(id) from login;";
	$query3 = "set @idNo=@idNo+1;";
	$query4 = "insert into login values(@idNo,'$username','$password');";
	$query5 = "commit;";
	mysqli_query($db, $query1) or die('Error querying database.');
	mysqli_query($db, $query2) or die('Error querying database.');
	mysqli_query($db, $query3) or die('Error querying database.');
	mysqli_query($db, $query4) or die('Error querying database.');
	mysqli_query($db, $query5) or die('Error querying database.');
	echo "Done Correctly!<br/>";
	mysqli_close($db);
?>