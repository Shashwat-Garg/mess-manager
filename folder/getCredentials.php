<?php
	$db = mysqli_connect('localhost','root','shshwtgrg','IDS')
	or die('Error connecting to MySQL server.');
	$id = 0;
	$username=$_POST['id'];
	$name=$_POST['name'];
	$password=$_POST['password'];
	$type=$_POST['type'];
	$hostel=$_POST['hostel'];
	$contact=$_POST['contact'];
	$messofmanager="";
	$query1 = "start transaction;";
	$query2 = "";
	if($type=="Student"){
		$query2 = "insert into totalstudents values('$username','$name','$hostel',$contact,'$password');";
	}
	else if($type=="Mess Manager"){
		$messofmanager=$_POST['messofmanager'];
		$query2 = "insert into messmanagers values('$messofmanager','$username','$name',$contact,'$password');";
	}
	else{
		$query2 = "insert into otherusers values('$username','$name','$type','$password','$hostel',$contact);";	
	}
	$query3 = "commit;";
	mysqli_query($db, $query1) or die('Error querying database.');
	mysqli_query($db, $query2) or die('Error querying database.');
	mysqli_query($db, $query3) or die('Error querying database.');
	echo "Success!";
	mysqli_close($db);
?>