package com.example.music.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.music.Adapter.DanhSachTheLoaiTheoChuDeAdapter;
import com.example.music.Model.ChuDe;
import com.example.music.Model.TheLoai;
import com.example.music.R;
import com.example.music.Retrofit.RetrofitClient;
import com.example.music.Util.ApiMusic;
import com.example.music.Util.Util;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DanhSachTheLoaiTheoChuDeActivity extends AppCompatActivity {
    ChuDe chuDe;
    Toolbar toolbar;
    RecyclerView recyclerView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiMusic apiMusic;
    List<TheLoai> theLoaiList;
    DanhSachTheLoaiTheoChuDeAdapter danhSachTheLoaiTheoChuDeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_the_loai_theo_chu_de);
        initView();
        GetIntent();
        actionBar();
        getData();
    }

    private void getData() {
        compositeDisposable.add(apiMusic.getTheLoaiTheoChuDe(chuDe.getIdChuDe())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        theLoaiModel -> {
                            if(theLoaiModel.isSuccess()){
                                theLoaiList = theLoaiModel.getResult();
                                danhSachTheLoaiTheoChuDeAdapter = new DanhSachTheLoaiTheoChuDeAdapter(getApplicationContext(), theLoaiList);
                                recyclerView.setAdapter(danhSachTheLoaiTheoChuDeAdapter);
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "Lỗi" + throwable.getMessage(), Toast.LENGTH_LONG).show();
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
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbartheloaitheochude);
        recyclerView = findViewById(R.id.recyclecviewtheloaitheochude);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        apiMusic = RetrofitClient.getInstance(Util.BASE_URL).create(ApiMusic.class);
    }

    private void GetIntent() {
        Intent intent = getIntent();
        if(intent.hasExtra("chude")){
            chuDe = (ChuDe) intent.getSerializableExtra("chude");
        }
    }
}