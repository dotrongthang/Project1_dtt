<?php

require "connect.php";

$id = $_POST['idNG'];
$hoten = $_POST['tenNG'];
$sdt = $_POST['sdtNG'];
$matkhau = $_POST['matkhauNG'];
$tiengui = $_POST['tienguiNG'];

if(strlen($id) > 0 && strlen($hoten) > 0 && strlen($sdt) > 0 && strlen($matkhau) > 0 && strlen($tiengui) > 0 ){
	$query = "UPDATE nguoigui SET hoten = '$hoten', sdt = '$sdt', matkhau = '$matkhau', tiengui = '$tiengui' WHERE id = '$id'";

if(mysqli_query($connect, $query)){
	echo "success";
}else{
	echo "error";
}
}else {
	echo "null";
}

?>