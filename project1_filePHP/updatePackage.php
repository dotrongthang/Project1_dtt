<?php

require "connect.php";

$id = $_POST['idBK'];
$tenbk = $_POST['tenBK'];
$nguoinhan = $_POST['nguoinhanBK'];
$noinhan = $_POST['noinhanBK'];

if(strlen($id) > 0 && strlen($tenbk) > 0 && strlen($noigui) > 0 && strlen($noinhan) > 0){
	$query = "UPDATE buukien SET tenbk = '$tenbk', nguoinhan = '$nguoinhan', noinhan = '$noinhan' WHERE id = '$id'";

if(mysqli_query($connect, $query)){
	echo "success";
}else{
	echo "error";
}
}else {
	echo "null";
}

?>