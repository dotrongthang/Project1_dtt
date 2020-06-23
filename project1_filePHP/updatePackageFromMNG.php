<?php

require "connect.php";

$id = $_POST['idBK'];
$tenbk = $_POST['tenBK'];
$nguoigui = $_POST['nguoiguiBK'];
$taixe = $_POST['taixeBK'];
$nguoinhan = $_POST['nguoinhanBK'];
$noinhan = $_POST['noinhanBK'];
$tinhtrang = $_POST['tinhtrangBK'];


if(strlen($id) > 0 && strlen($tenbk) > 0 && strlen($nguoigui) > 0 && strlen($taixe) > 0 && strlen($noigui) > 0 && strlen($noinhan) > 0 && strlen($tinhtrang) > 0){
	$query = "UPDATE buukien SET tenbk = '$tenbk', nguoigui = '$nguoigui', taixe = '$taixe', nguoinhan = '$nguoinhan', noinhan = '$noinhan', tinhtrang = '$tinhtrang' WHERE id = '$id'";

if(mysqli_query($connect, $query)){
	echo "success";
}else{
	echo "error";
}
}else {
	echo "null";
}

?>