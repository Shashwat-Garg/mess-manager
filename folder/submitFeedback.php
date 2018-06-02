<?php
	$db = mysqli_connect('localhost','root','shshwtgrg','IDS')
	or die('Error connecting to MySQL server.');
	$id = 0;
	$username=$_POST['username'];
	$desc=$_POST['description'];
	$rating=$_POST['rating'];
	$monthYear = date('F Y');
	$mess=$_POST['messname'];
	$query = "insert into feedback values('$username','$desc',$rating,'$monthYear','$mess');";	
	mysqli_query($db, $query) or die('Error querying database.');
	echo "Success!";
	mysqli_close($db);
?>