package com.example.music.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.Activity.DanhSachTatCaAlbumActivity;
import com.example.music.Adapter.AlbumAdapter;
import com.example.music.Model.Album;
import com.example.music.R;
import com.example.music.Retrofit.RetrofitClient;
import com.example.music.Util.ApiMusic;
import com.example.music.Util.Util;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Fragment_Album_Hot extends Fragment {

    View view;
    RecyclerView recyclerView;
    TextView txtxemthemAlbum;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiMusic apiMusic;
    List<Album> albumList;
    AlbumAdapter albumAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album_hot, container, false);

        initView();
        getData();
        inintControl();
        return view;
    }

    private void inintControl() {
        txtxemthemAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachTatCaAlbumActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        recyclerView = view.findViewById(R.id.recyclerviewAlbum);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        txtxemthemAlbum = view.findViewById(R.id.textviewxemthemAlbum);
        apiMusic = RetrofitClient.getInstance(Util.BASE_URL).create(ApiMusic.class);
        albumList = new ArrayList<>();
    }

    private void getData() {
        compositeDisposable.add(apiMusic.getAlbumHot()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        albumModel -> {
                            if(albumModel.isSuccess()){
                                albumList = albumModel.getResult();
                                albumAdapter = new AlbumAdapter(getContext(), albumList);
                                recyclerView.setAdapter(albumAdapter);
                            }
                        },
                        throwable -> {
                            //Toast.makeText(getContext(), "Lỗi Album: " + throwable.getMessage(), Toast.LENGTH_LONG).show();
                        }
                )
        );
    }
}
