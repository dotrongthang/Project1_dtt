package com.example.project1.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Driver implements Serializable {

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
@SerializedName("DoanhThu")
@Expose
private String doanhThu;

    public Driver(String iD, String hoTen, String sDT, String matKhau, String doanhThu) {
        this.iD = iD;
        this.hoTen = hoTen;
        this.sDT = sDT;
        this.matKhau = matKhau;
        this.doanhThu = doanhThu;
    }

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

public String getDoanhThu() {
return doanhThu;
}

public void setDoanhThu(String doanhThu) {
this.doanhThu = doanhThu;
}

}