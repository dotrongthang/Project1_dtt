<?php

require "connect.php";

$query = "SELECT * FROM nguoigui";

$data = mysqli_query($connect, $query);

//tạo class TaiXe
class NguoiGui{
	function NguoiGui($id, $hoten, $sdt, $matkhau, $tiengui){
		$this->ID = $id;
		$this->HoTen = $hoten;
		$this->SDT = $sdt;
		$this->MatKhau = $matkhau;
		$this->TienGui = $tiengui;
	}
}
// tạo mảng
$mangNG = array();
// thêm phần tử vào mảng
while ($row = mysqli_fetch_assoc($data)) {
	array_push($mangNG, new NguoiGui($row['id'], $row['hoten'], $row['sdt'], $row['matkhau'], $row['tiengui']));
}

//chuyển định dạng của mảng sang json
echo json_encode($mangNG);
?>
