<?php

require "connect.php";

$taiXeCu = $_POST['Old'];
$taiXeMoi = $_POST['New'];

if(strlen($taiXeCu) > 0 && strlen($taiXeMoi) > 0){
	$query = "UPDATE buukien SET taixe = '$taiXeMoi' WHERE taixe = '$taiXeCu'";

if(mysqli_query($connect, $query)){
	echo "success";
}else{
	echo "error";
}
}else {
	echo "null";
}

?>