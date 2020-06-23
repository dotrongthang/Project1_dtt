<?php

require "connect.php";

$sdtDr = $_POST['phoneDriver'];

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

if(strlen($sdtDr) > 0){
	// tạo mảng
	$mangTX = array();
// thêm phần tử vào mảng
	$query = "SELECT * FROM taixe WHERE FIND_IN_SET('$sdtDr', sdt)";

	$data = mysqli_query($connect, $query);


	if($data){
		while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangTX, new TaiXe($row['id'], $row['hoten'], $row['sdt'], $row['matkhau'], 
			$row['doanhthu']));
		}
	}
		if(count($mangTX)>0){
			//chuyển định dạng của mảng sang json
			echo json_encode($mangTX);
		}else{
			echo "Fail";
		}

}else{
	echo "Null";
}
?>
