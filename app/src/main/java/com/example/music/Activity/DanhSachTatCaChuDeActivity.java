package com.example.music.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.music.Adapter.DanhSachChuDeAdapter;
import com.example.music.Model.ChuDe;
import com.example.music.R;
import com.example.music.Retrofit.RetrofitClient;
import com.example.music.Util.ApiMusic;
import com.example.music.Util.Util;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DanhSachTatCaChuDeActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiMusic apiMusic;
    List<ChuDe> chuDeList;
    DanhSachChuDeAdapter danhSachChuDeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_tat_ca_chu_de);
        initView();
        actionBar();
        getData();
    }

    private void getData() {
        compositeDisposable.add(apiMusic.getChuDe()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        chuDeModel -> {
                            if(chuDeModel.isSuccess()){
                                chuDeList = chuDeModel.getResult();
                                danhSachChuDeAdapter = new DanhSachChuDeAdapter(getApplicationContext(), chuDeList);
                                recyclerView.setAdapter(danhSachChuDeAdapter);
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "Lỗi " + throwable.getMessage(), Toast.LENGTH_LONG).show();
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
        toolbar = findViewById(R.id.toolbarchude);
        recyclerView = findViewById(R.id.recyclerviewChude);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        apiMusic = RetrofitClient.getInstance(Util.BASE_URL).create(ApiMusic.class);
        chuDeList = new ArrayList<>();
    }
}