<?php

require "connect.php";

$phoneSender = $_POST['phoneSender'];

class NguoiGui{
	function NguoiGui($id, $hoten, $sdt, $matkhau, $tiengui){
		$this->ID = $id;
		$this->HoTen = $hoten;
		$this->SDT = $sdt;
		$this->MatKhau = $matkhau;
		$this->TienGui = $tiengui;
	}
}

if(strlen($phoneSender) > 0){
	// tạo mảng
	$mangNG = array();
// thêm phần tử vào mảng
	$query = "SELECT * FROM nguoigui WHERE FIND_IN_SET('$phoneSender', sdt)";

	$data = mysqli_query($connect, $query);


	if($data){
		while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangNG, new NguoiGui($row['id'], $row['hoten'], $row['sdt'], $row['matkhau'], 
			$row['tiengui']));
		}
	}
		if(count($mangNG)>0){
			//chuyển định dạng của mảng sang json
			echo json_encode($mangNG);
		}else{
			echo "Fail";
		}

}else{
	echo "Null";
}
?>
