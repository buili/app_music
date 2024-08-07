package com.example.music.Model;

import java.io.Serializable;

public class Playlist implements Serializable {
    private  int idPlayList;
    private String Ten;
    private String HinhAnh;
    private String HinhIcon;

    public int getIdPlayList() {
        return idPlayList;
    }

    public void setIdPlayList(int idPlayList) {
        this.idPlayList = idPlayList;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getHinhIcon() {
        return HinhIcon;
    }

    public void setHinhIcon(String hinhIcon) {
        HinhIcon = hinhIcon;
    }
}
