package com.example.music.Model;

import java.io.Serializable;

public class ChuDe implements Serializable {
    private int idChuDe;
    private String TenChuDe;
    private String HinhChuDe;

    public int getIdChuDe() {
        return idChuDe;
    }

    public void setIdChuDe(int idChuDe) {
        this.idChuDe = idChuDe;
    }

    public String getTenChuDe() {
        return TenChuDe;
    }

    public void setTenChuDe(String tenChuDe) {
        TenChuDe = tenChuDe;
    }

    public String getHinhChuDe() {
        return HinhChuDe;
    }

    public void setHinhChuDe(String hinhChuDe) {
        HinhChuDe = hinhChuDe;
    }
}
