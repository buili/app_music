package com.example.music.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.os.Parcelable;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.music.Adapter.DanhsachbaihatAdapter;
import com.example.music.Model.Album;
import com.example.music.Model.BaiHat;
import com.example.music.Model.Playlist;
import com.example.music.Model.QuangCao;
import com.example.music.Model.TheLoai;
import com.example.music.R;
import com.example.music.Retrofit.RetrofitClient;
import com.example.music.Util.ApiMusic;
import com.example.music.Util.Util;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DanhSachBaiHatActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    ImageView imgdanhsachcakhuc;
    QuangCao quangCao;
    Playlist playlist;
    TheLoai theLoai;
    Album album;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiMusic apiMusic;
    List<BaiHat> baiHatList;
    //ArrayList<BaiHat> baiHatList;

    BaiHat baiHat;
    DanhsachbaihatAdapter danhsachbaihatAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        initView();
        actionBar();
        DataIntent();
        if(quangCao != null && !quangCao.getTenBaiHat().equals("")){
              setValueInView(quangCao.getTenBaiHat(), quangCao.getHinhBaiHat());
            getDataQuangcao(quangCao.getId());
            //Log.d("DanhSach", quangCao.getTenBaiHat());
//            Toast.makeText(getApplicationContext(), quangCao.getTenBaiHat(), Toast.LENGTH_LONG).show();
            Log.d("DanhSach", "QuangCao yes");
        }else {
            Log.d("DanhSach", "QuangCao is null or has invalid data");
        }
        if(playlist != null && !playlist.getTen().equals("")){
            setValueInView(playlist.getTen(),playlist.getHinhAnh());
            getDataPlaylist(playlist.getIdPlayList());
        }
        if(theLoai != null && !theLoai.getTenTheLoai().equals("")){
            setValueInView(theLoai.getTenTheLoai(),theLoai.getHinhTheLoai());
            getDataTheLoai(theLoai.getIdTheLoai());
        }
        if(album != null && !album.getTenAlbum().equals("")){
            setValueInView(album.getTenAlbum(),album.getHinhAlbum());
            getDataAlbum(album.getIdAlbum());
        }


    }

    private void getDataAlbum(int idAlbum) {
        compositeDisposable.add(apiMusic.getDanhSachBaiHatTheoAlbum(idAlbum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        baiHatModel -> {
                            if(baiHatModel.isSuccess()){
                                baiHatList = baiHatModel.getResult();
                                danhsachbaihatAdapter = new DanhsachbaihatAdapter(getApplicationContext(), baiHatList);
                                recyclerView.setAdapter(danhsachbaihatAdapter);
                                eventClick();
                            } else {
                                Log.d("DanhSachNhan", "API call failed");
                            }
                        },
                        throwable -> {
                            Log.d("DanhSachNhan", throwable.getMessage());
                            //Toast.makeText(getApplicationContext(), "Lỗi bài hát: " + throwable.getMessage(), Toast.LENGTH_LONG).show();
                        }
                )
        );
    }

    private void getDataTheLoai(int idTheLoai) {
        compositeDisposable.add(apiMusic.getDanhsachbaihattheotheloai(idTheLoai)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        baiHatModel -> {
                            if(baiHatModel.isSuccess()){
                                baiHatList = baiHatModel.getResult();
                                danhsachbaihatAdapter = new DanhsachbaihatAdapter(getApplicationContext(), baiHatList);
                                recyclerView.setAdapter(danhsachbaihatAdapter);
                                eventClick();
                            } else {
                                Log.d("DanhSachNhan", "API call failed");
                            }
                        },
                        throwable -> {
                            Log.d("DanhSachNhan", throwable.getMessage());
                            //Toast.makeText(getApplicationContext(), "Lỗi bài hát: " + throwable.getMessage(), Toast.LENGTH_LONG).show();
                        }
                )
        );
    }

    private void getDataPlaylist(int id) {
        compositeDisposable.add(apiMusic.getDanhsachbaihattheoplaylist(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        baiHatModel -> {
                            if(baiHatModel.isSuccess()){
                                baiHatList = baiHatModel.getResult();
                                danhsachbaihatAdapter = new DanhsachbaihatAdapter(getApplicationContext(), baiHatList);
                                recyclerView.setAdapter(danhsachbaihatAdapter);
                                eventClick();
                            } else {
                                Log.d("DanhSachNhan", "API call failed");
                            }
                        },
                        throwable -> {
                            Log.d("DanhSachNhan", throwable.getMessage());
                            //Toast.makeText(getApplicationContext(), "Lỗi bài hát: " + throwable.getMessage(), Toast.LENGTH_LONG).show();
                        }
                )
        );
    }
//    private void setValueInView(String ten, String hinh) {
//        collapsingToolbarLayout.setTitle(ten);
//        try {
//            URL url = new URL(hinh);
//            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
//            collapsingToolbarLayout.setBackground(bitmapDrawable);
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Glide.with(this)
//                .load(hinh)
//                .placeholder(R.drawable.noanh)
//                .error(R.drawable.error)
//                .into(imgdanhsachcakhuc);
//    }

    private void setValueInView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);

        // Sử dụng Glide để tải ảnh và thiết lập làm background cho collapsingToolbarLayout
        Glide.with(this)
                .asBitmap()
                .load(hinh)
                .placeholder(R.drawable.noanh)
                .error(R.drawable.error)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), resource);
                        collapsingToolbarLayout.setBackground(bitmapDrawable);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        // Xử lý khi ảnh tải bị hủy
                    }
                });

        // Sử dụng Glide để tải ảnh vào imgdanhsachcakhuc
        Glide.with(this)
                .load(hinh)
                .placeholder(R.drawable.noanh)
                .error(R.drawable.error)
                .into(imgdanhsachcakhuc);
    }
    private void getDataQuangcao(int idquangcao) {
        compositeDisposable.add(apiMusic.getDanhsachbaihattheoquangcao(idquangcao)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        baiHatModel -> {
                            if(baiHatModel.isSuccess()){
                                baiHatList = baiHatModel.getResult();
                                danhsachbaihatAdapter = new DanhsachbaihatAdapter(getApplicationContext(), baiHatList);
                                recyclerView.setAdapter(danhsachbaihatAdapter);
                                eventClick();
                            } else {
                                Log.d("DanhSachNhan", "API call failed");
                            }
                        },
                        throwable -> {
                            Log.d("DanhSachNhan", throwable.getMessage());
                            //Toast.makeText(getApplicationContext(), "Lỗi bài hát: " + throwable.getMessage(), Toast.LENGTH_LONG).show();
                        }
                )
        );
    }


    private void actionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
    }

    private void initView() {
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbardanhsach);
        recyclerView = findViewById(R.id.recycleviewdanhsachbaihat);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
        imgdanhsachcakhuc = findViewById(R.id.imageviewdanhsachcakhuc);
        apiMusic = RetrofitClient.getInstance(Util.BASE_URL).create(ApiMusic.class);
        baiHatList = new ArrayList<>();

        floatingActionButton.setEnabled(false);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("banner")){
                quangCao = (QuangCao) intent.getSerializableExtra("banner");
                Log.d("DanhSach", quangCao.getTenBaiHat());
              //  Toast.makeText(getApplicationContext(), quangCao.getTenBaiHat(), Toast.LENGTH_LONG).show();
            }
            if(intent.hasExtra("itemplaylist")){
                playlist = (Playlist) intent.getSerializableExtra("itemplaylist");
            }
            if(intent.hasExtra("idtheloai")){
                theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");
            }
            if(intent.hasExtra("album")){
                album = (Album) intent.getSerializableExtra("album");
            }
        }
    }

    private void eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlayNhacActivity.class);
                //intent.putExtra("cacbaihat", (Parcelable) baiHatList);
                intent.putParcelableArrayListExtra("cacbaihat", (ArrayList<? extends Parcelable>) baiHatList);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}