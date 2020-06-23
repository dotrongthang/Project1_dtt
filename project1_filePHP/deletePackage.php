<?php

require "connect.php";

$id = $_POST['idBK'];

if(strlen($id) > 0){
	$query = "DELETE FROM buukien WHERE id = '$id'";

if(mysqli_query($connect, $query)){
	echo "success";
}else{
	echo "error";
}
}else {
	echo "null";
}

?>