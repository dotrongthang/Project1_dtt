<?php

require "connect.php";

$taixe = $_POST['taixeBK'];
$tinhtrang = $_POST['tinhtrangBKN'];
// $taixe = "1234";
// $tinhtrang = "đã gửi";

//tạo class TaiXe
class BuuKien{
	function BuuKien($id, $tenBK, $nguoiguiBK, $taixeBK, $nguoinhanBK, $noinhanBK, $tinhtrangBK){
		$this->ID = $id;
		$this->TenBK = $tenBK;
		$this->NguoiGui = $nguoiguiBK;
		$this->TaiXe = $taixeBK;
		$this->NguoiNhan = $nguoinhanBK;
		$this->NoiNhan = $noinhanBK;
		$this->TinhTrang = $tinhtrangBK;

	}
}

if(strlen($taixe) > 0 && strlen($tinhtrang) > 0){
	// tạo mảng
	$mangBK = array();
// thêm phần tử vào mảng
	$query = "SELECT * FROM buukien WHERE FIND_IN_SET('$taixe', taixe) AND FIND_IN_SET('$tinhtrang', tinhtrang)";

	$data = mysqli_query($connect, $query);


	if($data){
		while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangBK, new BuuKien($row['id'], $row['tenbk'], $row['nguoigui'], $row['taixe'], $row['nguoinhan'], $row['noinhan'], $row['tinhtrang']));
		}
	}
		if(count($mangBK)>0){
			//chuyển định dạng của mảng sang json
			echo json_encode($mangBK);
		}else{
			echo "Fail";
		}

}else{
	echo "Null";
}

?>
