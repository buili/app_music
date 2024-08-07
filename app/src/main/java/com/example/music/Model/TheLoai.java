package com.example.music.Model;

import java.io.Serializable;

public class TheLoai implements Serializable {
    private int idTheLoai;
    private String idChuDe;
    private String TenTheLoai;
    private String HinhTheLoai;

    public int getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(int idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    public String getIdChuDe() {
        return idChuDe;
    }

    public void setIdChuDe(String idChuDe) {
        this.idChuDe = idChuDe;
    }

    public String getTenTheLoai() {
        return TenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        TenTheLoai = tenTheLoai;
    }

    public String getHinhTheLoai() {
        return HinhTheLoai;
    }

    public void setHinhTheLoai(String hinhTheLoai) {
        HinhTheLoai = hinhTheLoai;
    }
}
