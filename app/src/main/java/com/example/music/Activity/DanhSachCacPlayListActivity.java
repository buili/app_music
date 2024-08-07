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

import com.example.music.Adapter.DanhSachCacPlayListAdapter;
import com.example.music.Model.Playlist;
import com.example.music.R;
import com.example.music.Retrofit.RetrofitClient;
import com.example.music.Util.ApiMusic;
import com.example.music.Util.Util;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DanhSachCacPlayListActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    List<Playlist> playlistList;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    DanhSachCacPlayListAdapter danhSachCacPlayListAdapter;
    ApiMusic apiMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_cac_play_list);
        initView();
        actionBar();
        getData();
    }

    private void getData() {
        compositeDisposable.add(apiMusic.getDanhSachCacPlayList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        playlistModel -> {
                            if(playlistModel.isSuccess()){
                                playlistList = playlistModel.getResult();
                                //Log.d("PlayList" , playlistList.get(0).getTen());
                                danhSachCacPlayListAdapter = new DanhSachCacPlayListAdapter(this, playlistList);
                                recyclerView.setAdapter(danhSachCacPlayListAdapter);
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "Lỗi playlist" + throwable.getMessage(), Toast.LENGTH_LONG).show();
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
        toolbar = findViewById(R.id.toolbardanhsachplaylist);
        recyclerView = findViewById(R.id.recycleviewdanhsachcacplaylist);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        playlistList = new ArrayList<>();
        apiMusic = RetrofitClient.getInstance(Util.BASE_URL).create(ApiMusic.class);
    }
}