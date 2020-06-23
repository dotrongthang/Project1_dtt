<?php

require "connect.php";

$id = $_POST['idBK'];
$taixe = $_POST['taixeBK'];


if(strlen($id) > 0 && strlen($taixe) > 0){
	$query = "UPDATE buukien SET taixe = '$taixe' WHERE id = '$id'";

if(mysqli_query($connect, $query)){
	echo "success";
}else{
	echo "error";
}
}else {
	echo "null";
}

?>