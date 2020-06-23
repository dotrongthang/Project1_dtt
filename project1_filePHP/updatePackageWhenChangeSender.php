<?php

require "connect.php";

$NGCu = $_POST['OldS'];
$NGMoi = $_POST['NewS'];

if(strlen($NGCu) > 0 && strlen($NGMoi) > 0){
	$query = "UPDATE buukien SET nguoigui = '$NGMoi' WHERE nguoigui = '$NGCu'";

if(mysqli_query($connect, $query)){
	echo "success";
}else{
	echo "error";
}
}else {
	echo "null";
}

?>