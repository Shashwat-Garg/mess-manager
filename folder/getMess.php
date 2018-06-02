<?php
	$db = mysqli_connect('localhost','root','shshwtgrg','IDS')
	or die('Error connecting to MySQL server.');
	$id = 0;
	$userid=$_POST['userid'];
	$type=$_POST['type'];
	$query="";
	$resp="";
	$messflag=false;
	if(type=="student"){
		$query="select * from d1chosen where rollno='$userid';";
		$result=mysqli_query($db, $query) or die('Error querying database.');
		while($row=mysqli_fetch_array($result)){
			$resp="D1";
			$messflag=true;
			break;
		}
		if($messflag==false){
			$query="select * from d2chosen where rollno='$userid';";
			$result=mysqli_query($db, $query) or die('Error querying database.');
			while($row=mysqli_fetch_array($result)){
				$resp="D2";
				$messflag=true;
				break;
			}
			if($messflag==false){
				$query="select * from d9chosen where rollno='$userid';";
				$result=mysqli_query($db, $query) or die('Error querying database.');
				while($row=mysqli_fetch_array($result)){
					$resp="D9";
					$messflag=true;
					break;
				}
			}
		}
	}
	else if($type=="manager"){
		$query = "select * from messmanagers where username='$userid';";
				$result=mysqli_query($db, $query) or die('Error querying database.');
				while($row=mysqli_fetch_array($result)){
					$resp=$row['messname'];
					$messflag=true;
					break;
				}
	}
	$response["messname"]=$resp;
	echo json_encode($response);
	mysqli_close($db);
?>