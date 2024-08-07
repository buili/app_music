package com.example.music.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.music.Adapter.AllAlbumAdpter;
import com.example.music.Adapter.DanhSachChuDeAdapter;
import com.example.music.Model.Album;
import com.example.music.Model.BaiHat;
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

public class DanhSachTatCaAlbumActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiMusic apiMusic;
    List<Album> albumList;
    AllAlbumAdpter allAlbumAdpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_tat_ca_album);
        initView();
        actionBar();
        getData();
    }

    private void getData() {
        compositeDisposable.add(apiMusic.getTatCaAlbum()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        albumModel -> {
                            if(albumModel.isSuccess()){
                                albumList = albumModel.getResult();
                                allAlbumAdpter = new AllAlbumAdpter(getApplicationContext(), albumList);
                                recyclerView.setAdapter(allAlbumAdpter);
                                Log.d("Album", "Thành công");
                            }else{
                                Log.d("Album", "Lỗi");
                            }
                        },
                        throwable -> {
                            Log.d("Album", throwable.getMessage());
                          //  Toast.makeText(getApplicationContext(), "Lỗi " + throwable.getMessage(), Toast.LENGTH_LONG).show();
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
        toolbar = findViewById(R.id.toolbaralbum);
        recyclerView = findViewById(R.id.recyclerviewallalbum);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        apiMusic = RetrofitClient.getInstance(Util.BASE_URL).create(ApiMusic.class);
        albumList = new ArrayList<>();
    }
}