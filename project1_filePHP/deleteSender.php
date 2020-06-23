<?php

require "connect.php";

$id = $_POST['idNG'];

if(strlen($id) > 0){
	$query = "DELETE FROM nguoigui WHERE id = '$id'";

if(mysqli_query($connect, $query)){
	echo "success";
}else{
	echo "error";
}
}else {
	echo "null";
}

?>