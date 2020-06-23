<?php

require "connect.php";

$sdt = $_POST['sdtTX'];
$doanhthu = $_POST['doanhthuTC'];

if(strlen($sdt) > 0 && strlen($doanhthu) > 0){
	$query = "UPDATE taixe SET doanhthu = '$doanhthu' WHERE sdt = '$sdt'";

if(mysqli_query($connect, $query)){
	echo "success";
}else{
	echo "error";
}
}else {
	echo "null";
}

?>