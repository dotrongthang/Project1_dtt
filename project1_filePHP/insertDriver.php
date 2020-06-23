<?php

require "connect.php";

$ten = $_POST['tenTX'];
$sdt = $_POST['sdtTX'];
$matkhau = $_POST['matkhauTX'];
$tien = $_POST['tienTX'];

$query = "INSERT INTO taixe VALUES (null, '$ten', '$sdt', '$matkhau', '$tien')";

if(mysqli_query($connect, $query)){
	echo "success";
}else{
	echo "error";
}

?>