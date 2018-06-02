<?php
	$db = mysqli_connect('localhost','root','shshwtgrg','IDS')
	or die('Error connecting to MySQL server.');
	$id = 0;
	$username=$_POST['userid'];
	$messchoice=$_POST['messname'];
	$query1 = "start transaction;";
	$query2 = "select @time:=UNIX_TIMESTAMP();";
	#$query3 = "set time=$date->getTimestamp();";
	$query4='';
	if($messchoice=="D1")
		$query4 = "insert into d1chosen values(@time,'$username');";
	else if($messchoice=="D2")
		$query4 = "insert into d2chosen values(@time,'$username');";
	else if($messchoice=="D9")
		$query4 = "insert into d9chosen values(@time,'$username');";
	else{
		echo "ERROR!";
		exit(0);
	}
	$query5 = "commit;";
	mysqli_query($db, $query1) or die('Error querying database.');
	mysqli_query($db, $query2) or die('Error querying database.');
	#mysqli_query($db, $query3) or die('Error querying database.');
	mysqli_query($db, $query4) or die('Error querying database.');
	mysqli_query($db, $query5) or die('Error querying database.');
	echo "Done Correctly!<br/>";
	mysqli_close($db);
?>