package com.example.music.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class BaiHat implements Parcelable {
    private  int idBaiHat;
    private String idAlbum;
    private String idTheLoai;
    private String idPlayList;
    private String TenBaiHat;
    private String HinhBaiHat;
    private String CaSi;
    private String LinkBaiHat;
    private String LuotThich;

    protected BaiHat(Parcel in) {
        idBaiHat = in.readInt();
        idAlbum = in.readString();
        idTheLoai = in.readString();
        idPlayList = in.readString();
        TenBaiHat = in.readString();
        HinhBaiHat = in.readString();
        CaSi = in.readString();
        LinkBaiHat = in.readString();
        LuotThich = in.readString();
    }

    public static final Creator<BaiHat> CREATOR = new Creator<BaiHat>() {
        @Override
        public BaiHat createFromParcel(Parcel in) {
            return new BaiHat(in);
        }

        @Override
        public BaiHat[] newArray(int size) {
            return new BaiHat[size];
        }
    };

    public int getIdBaiHat() {
        return idBaiHat;
    }

    public void setIdBaiHat(int idBaiHat) {
        this.idBaiHat = idBaiHat;
    }

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(String idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    public String getIdPlayList() {
        return idPlayList;
    }

    public void setIdPlayList(String idPlayList) {
        this.idPlayList = idPlayList;
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

    public String getCaSi() {
        return CaSi;
    }

    public void setCaSi(String caSi) {
        CaSi = caSi;
    }

    public String getLinkBaiHat() {
        return LinkBaiHat;
    }

    public void setLinkBaiHat(String linkBaiHat) {
        LinkBaiHat = linkBaiHat;
    }

    public String getLuotThich() {
        return LuotThich;
    }

    public void setLuotThich(String luotThich) {
        LuotThich = luotThich;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(idBaiHat);
        dest.writeString(idAlbum);
        dest.writeString(idTheLoai);
        dest.writeString(idPlayList);
        dest.writeString(TenBaiHat);
        dest.writeString(HinhBaiHat);
        dest.writeString(CaSi);
        dest.writeString(LinkBaiHat);
        dest.writeString(LuotThich);
    }
}
