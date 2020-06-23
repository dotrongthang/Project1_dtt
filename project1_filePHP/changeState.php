<?php

require "connect.php";

$id = $_POST['idBK'];
$tinhtrang = $_POST['tinhtrangBK'];

if(strlen($id) > 0 && strlen($tinhtrang) > 0){
	$query = "UPDATE buukien SET tinhtrang = '$tinhtrang' WHERE id = '$id'";

if(mysqli_query($connect, $query)){
	echo "success";
}else{
	echo "error";
}
}else {
	echo "null";
}

?>