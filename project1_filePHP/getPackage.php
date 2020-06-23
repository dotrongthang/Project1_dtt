<?php

require "connect.php";

$query = "SELECT * FROM buukien";

$data = mysqli_query($connect, $query);

//tạo class BuuKien
class BuuKien{
	function BuuKien($id, $tenbk, $nguoigui, $taixe, $nguoinhan, $noinhan, $tinhtrang){
		$this->ID = $id;
		$this->TenBK = $tenbk;
		$this->NguoiGui = $nguoigui;
		$this->TaiXe = $taixe;
		$this->NguoiNhan = $nguoinhan;
		$this->NoiNhan = $noinhan;
		$this->TinhTrang = $tinhtrang;

	}
}
// tạo mảng
$mangBK = array();
// thêm phần tử vào mảng
while ($row = mysqli_fetch_assoc($data)) {
	array_push($mangBK, new BuuKien($row['id'], $row['tenbk'], $row['nguoigui'], $row['taixe'], $row['nguoinhan'], $row['noinhan'], $row['tinhtrang']));
}

//chuyển định dạng của mảng sang json
echo json_encode($mangBK);
?>
