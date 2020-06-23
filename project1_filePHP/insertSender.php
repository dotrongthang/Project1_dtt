<?php

require "connect.php";

$ten = $_POST['tenNG'];
$sdt = $_POST['sdtNG'];
$matkhau = $_POST['matkhauNG'];
$tien = $_POST['tienNG'];

$query = "INSERT INTO nguoigui VALUES (null, '$ten', '$sdt', '$matkhau', '$tien')";

if(mysqli_query($connect, $query)){
	echo "success";
}else{
	echo "error";
}

?>