package com.example.music.Model;

import java.io.Serializable;

public class Album implements Serializable {
    private int idAlbum;
    private String TenAlbum;
    private String TenCaSiAlbum;
    private String HinhAlbum;

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getTenAlbum() {
        return TenAlbum;
    }

    public void setTenAlbum(String tenAlbum) {
        TenAlbum = tenAlbum;
    }

    public String getTenCaSiAlbum() {
        return TenCaSiAlbum;
    }

    public void setTenCaSiAlbum(String tenCaSiAlbum) {
        TenCaSiAlbum = tenCaSiAlbum;
    }

    public String getHinhAlbum() {
        return HinhAlbum;
    }

    public void setHinhAlbum(String hinhAlbum) {
        HinhAlbum = hinhAlbum;
    }
}
