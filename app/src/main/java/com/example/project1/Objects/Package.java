package com.example.project1.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Package implements Serializable {

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("TenBK")
    @Expose
    private String tenBK;
    @SerializedName("NguoiGui")
    @Expose
    private String nguoiGui;
    @SerializedName("TaiXe")
    @Expose
    private String taiXe;
    @SerializedName("NguoiNhan")
    @Expose
    private String nguoiNhan;
    @SerializedName("NoiNhan")
    @Expose
    private String noiNhan;
    @SerializedName("TinhTrang")
    @Expose
    private String tinhTrang;

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getTenBK() {
        return tenBK;
    }

    public void setTenBK(String tenBK) {
        this.tenBK = tenBK;
    }

    public String getNguoiGui() {
        return nguoiGui;
    }

    public void setNguoiGui(String nguoiGui) {
        this.nguoiGui = nguoiGui;
    }

    public String getTaiXe() {
        return taiXe;
    }

    public void setTaiXe(String taiXe) {
        this.taiXe = taiXe;
    }

    public String getNguoiNhan() {
        return nguoiNhan;
    }

    public void setNguoiNhan(String nguoiNhan) {
        this.nguoiNhan = nguoiNhan;
    }

    public String getNoiNhan() {
        return noiNhan;
    }

    public void setNoiNhan(String noiNhan) {
        this.noiNhan = noiNhan;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

}