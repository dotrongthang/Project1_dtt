<?php

require "connect.php";

$sdt = $_POST['sdtNG'];
$tiengui = $_POST['tienguiTC'];

if(strlen($sdt) > 0 && strlen($tiengui) > 0){
	$query = "UPDATE nguoigui SET tiengui = '$tiengui' WHERE sdt = '$sdt'";

if(mysqli_query($connect, $query)){
	echo "success";
}else{
	echo "error";
}
}else {
	echo "null";
}

?>