package com.example.music.Util;


import com.example.music.Model.AlbumModel;
import com.example.music.Model.BaiHatModel;
import com.example.music.Model.ChuDeModel;
import com.example.music.Model.ChuDe_TheLoai_Model;
import com.example.music.Model.PlaylistModel;
import com.example.music.Model.QuangCaoModel;
import com.example.music.Model.TheLoaiModel;
import com.example.music.Model.UpdateModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiMusic {
    @GET("songbanner.php")
    Observable<QuangCaoModel> getsongbanner();

    @GET("playlistforcurrentday.php")
    Observable<PlaylistModel> getplaylistforcurrentday();

    @GET("chudevatheloaitrongngay.php")
    Observable<ChuDe_TheLoai_Model> getChudeTheloai();

    @GET("albumhot.php")
    Observable<AlbumModel> getAlbumHot();

    @GET("baihatduocyeuthich.php")
    Observable<BaiHatModel> getBaiHatHot();

    @POST("danhsachbaihat.php")
    @FormUrlEncoded
    Observable<BaiHatModel> getDanhsachbaihattheoquangcao(
            @Field("idquangcao") int idquangcao
    );

    @POST("danhsachbaihat.php")
    @FormUrlEncoded
    Observable<BaiHatModel> getDanhsachbaihattheoplaylist(
            @Field("idplaylist") int idplaylist
    );

    @POST("danhsachbaihat.php")
    @FormUrlEncoded
    Observable<BaiHatModel> getDanhsachbaihattheotheloai(
            @Field("idtheloai") int idtheloai
    );

    @GET("danhsachcacplaylist.php")
    Observable<PlaylistModel> getDanhSachCacPlayList();

    @GET("tatcachude.php")
    Observable<ChuDeModel> getChuDe();

    @POST("theloaitheochude.php")
    @FormUrlEncoded
    Observable<TheLoaiModel> getTheLoaiTheoChuDe(
            @Field("idchude") int idchude
    );

    @GET("tatcaalbum.php")
    Observable<AlbumModel> getTatCaAlbum();

    @POST("danhsachbaihat.php")
    @FormUrlEncoded
    Observable<BaiHatModel> getDanhSachBaiHatTheoAlbum(
            @Field("idalbum") int idalbum
    );

    @POST("updateluotthich.php")
    @FormUrlEncoded
    Observable<UpdateModel> updateLuotThich(
            @Field("luotthich") int luotthich,
            @Field("idbaihat") int idbaihat
    );

    @POST("searchbaihat.php")
    @FormUrlEncoded
    Observable<BaiHatModel> getSearchBaiHat(
      @Field("tukhoa") String tukhoa
    );

}
