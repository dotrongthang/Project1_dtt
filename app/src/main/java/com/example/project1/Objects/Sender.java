package com.example.project1.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sender implements Serializable {

@SerializedName("ID")
@Expose
private String iD;
@SerializedName("HoTen")
@Expose
private String hoTen;
@SerializedName("SDT")
@Expose
private String sDT;
@SerializedName("MatKhau")
@Expose
private String matKhau;
@SerializedName("TienGui")
@Expose
private String tienGui;

public String getID() {
return iD;
}

public void setID(String iD) {
this.iD = iD;
}

public String getHoTen() {
return hoTen;
}

public void setHoTen(String hoTen) {
this.hoTen = hoTen;
}

public String getSDT() {
return sDT;
}

public void setSDT(String sDT) {
this.sDT = sDT;
}

public String getMatKhau() {
return matKhau;
}

public void setMatKhau(String matKhau) {
this.matKhau = matKhau;
}

public String getTienGui() {
return tienGui;
}

public void setTienGui(String tienGui) {
this.tienGui = tienGui;
}

}