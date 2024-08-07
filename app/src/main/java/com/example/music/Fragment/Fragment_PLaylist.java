package com.example.music.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.example.music.Activity.DanhSachCacPlayListActivity;
import com.example.music.Adapter.PlaylistAdapter;
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

public class Fragment_PLaylist extends Fragment {
    View view;
    List<Playlist> playlistList;
    RecyclerView recyclerView;
    TextView txttitleplaylis, txtviewxemthemplaylist;
    PlaylistAdapter playlistAdapter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiMusic apiMusic = RetrofitClient.getInstance(Util.BASE_URL).create(ApiMusic.class);
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_playlist, container, false);
       initView();
       GetData();
       initControl();
       return  view;
    }

    private void initControl() {
        txtviewxemthemplaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachCacPlayListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        recyclerView = view.findViewById(R.id.recyclerviewplaylist);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        txttitleplaylis = view.findViewById(R.id.textviewtitleplaylist);
        txtviewxemthemplaylist = view.findViewById(R.id.textviewmoreplaylist);
        playlistList = new ArrayList<>();
    }

    private void GetData() {
        compositeDisposable.add(apiMusic.getplaylistforcurrentday()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        playlistModel -> {
                            if(playlistModel.isSuccess()){
                                playlistList = playlistModel.getResult();
                                //Log.d("Playlist", playlistList.get(0).getTen());
                                playlistAdapter = new PlaylistAdapter(getContext(), playlistList);
                                recyclerView.setAdapter(playlistAdapter);

                            }
                        },
                        throwable -> {
                            //Toast.makeText(getContext(), "Lỗi playlist" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                        }
                )
        );
    }
}
