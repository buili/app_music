package com.example.music.Model;

import java.io.Serializable;

public class QuangCao implements Serializable {
    private int id;
    private String HinhAnh;
    private String NoiDung;
    private String idBaiHat;
    private String TenBaiHat;
    private String HinhBaiHat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getIdBaiHat() {
        return idBaiHat;
    }

    public void setIdBaiHat(String idBaiHat) {
        this.idBaiHat = idBaiHat;
    }

    public String getTenBaiHat() {
        return TenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        TenBaiHat = tenBaiHat;
    }

    public String getHinhBaiHat() {
        return HinhBaiHat;
    }

    public void setHinhBaiHat(String hinhBaiHat) {
        HinhBaiHat = hinhBaiHat;
    }
}
