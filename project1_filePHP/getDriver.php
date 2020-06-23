<?php

require "connect.php";

$query = "SELECT * FROM taixe";

$data = mysqli_query($connect, $query);

//tạo class TaiXe
class TaiXe{
	function TaiXe($id, $hoten, $sdt, $matkhau, $doanhthu){
		$this->ID = $id;
		$this->HoTen = $hoten;
		$this->SDT = $sdt;
		$this->MatKhau = $matkhau;
		$this->DoanhThu = $doanhthu;
	}
}
// tạo mảng
$mangTX = array();
// thêm phần tử vào mảng
while ($row = mysqli_fetch_assoc($data)) {
	array_push($mangTX, new TaiXe($row['id'], $row['hoten'], $row['sdt'], $row['matkhau'], $row['doanhthu']));
}

//chuyển định dạng của mảng sang json
echo json_encode($mangTX);
?>
