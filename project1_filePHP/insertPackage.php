<?php

require "connect.php";

$tenbk = $_POST['tenBK'];
$nguoigui = $_POST['nguoiguiBK'];
$nguoinhan = $_POST['nguoinhanBK'];
$noinhan = $_POST['noinhanBK'];
$tinhtrang = $_POST['tinhtrangBK'];

$query = "INSERT INTO buukien VALUES (null, '$tenbk', '$nguoigui', 'chưa có', '$nguoinhan', '$noinhan', '$tinhtrang')";

if(mysqli_query($connect, $query)){
	echo "success";
}else{
	echo "error";
}

?>