<?php

require "connect.php";

$id = $_POST['idTX'];
$hoten = $_POST['tenTX'];
$sdt = $_POST['sdtTX'];
$matkhau = $_POST['matkhauTX'];
$doanhthu = $_POST['doanhthuTX'];

if(strlen($id) > 0 && strlen($hoten) > 0 && strlen($sdt) > 0 && strlen($matkhau) > 0 && strlen($doanhthu) > 0 ){
	$query = "UPDATE taixe SET hoten = '$hoten', sdt = '$sdt', matkhau = '$matkhau', doanhthu = '$doanhthu' WHERE id = '$id'";

if(mysqli_query($connect, $query)){
	echo "success";
}else{
	echo "error";
}
}else {
	echo "null";
}

?>