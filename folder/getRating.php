<?php
	$db = mysqli_connect('localhost','root','','IDS')
	or die('Error connecting to MySQL server.');
	$id = 0;
	$mess=$_POST['messname'];
	$userid = $_POST['userid'];
	$query1="select rating from messrating where messname='$mess';";
	$query2="select name from totalstudents where rollno='$userid';";
	$result = mysqli_query($db, $query1);
	$response["rating"]=0.0;
	$response["username"]="none";
	while ($row = mysqli_fetch_array($result)){
		$response["rating"]=$row['rating'];
	}
	$result = mysqli_query($db, $query2) or die("Error querying");
	while ($row = mysqli_fetch_array($result)){
		$response["username"]=$row['name'];
	}
	echo json_encode($response);
	mysqli_close($db);
?>