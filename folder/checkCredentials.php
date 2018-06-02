<?php
	$db = mysqli_connect('localhost','root','shshwtgrg','IDS')
	or die('Error connecting to MySQL server.');
	$id = 0;
	$username=$_POST['username'];
	$password=$_POST['password'];
	$type=$_POST['type'];
	$query2="";
	if($type=="student"){
		$query2="select * from totalstudents where rollno='$username';";
	}
	else if($type=="manager"){
		$query2="select * from messmanagers where username='$username';";
	}
	else{
		$query2="select * from otherusers where id='$username';";
	}
	$result = mysqli_query($db, $query2);
	$response["success"]=false;
	$response["username"]=$username;
	while ($row = mysqli_fetch_array($result)){
		if($password == (string)$row['password']){
			$response["success"]=true;
			break;
		}
	}
	echo json_encode($response);
	mysqli_close($db);
?>