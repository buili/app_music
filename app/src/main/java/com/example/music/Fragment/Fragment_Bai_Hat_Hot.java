package com.example.music.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.Adapter.BaiHatHotAdapter;
import com.example.music.Model.BaiHat;
import com.example.music.R;
import com.example.music.Retrofit.RetrofitClient;
import com.example.music.Util.ApiMusic;
import com.example.music.Util.Util;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Fragment_Bai_Hat_Hot extends Fragment {

    View view;
    RecyclerView recyclerView;

    ApiMusic apiMusic;
    List<BaiHat> baiHatList;
    BaiHatHotAdapter baiHatHotAdapter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bai_hat_thich_nhat, container, false);
        initView();
        getData();
        return view;
    }

    private void initView() {
        apiMusic = RetrofitClient.getInstance(Util.BASE_URL).create(ApiMusic.class);
        baiHatList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerviewbaihathot);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    private void getData() {
        compositeDisposable.add(apiMusic.getBaiHatHot()
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        baiHatModel -> {
                            if(baiHatModel.isSuccess()){
                                baiHatList = baiHatModel.getResult();
                                baiHatHotAdapter = new BaiHatHotAdapter(getContext(), baiHatList);
                                recyclerView.setAdapter(baiHatHotAdapter);
                            }
                        },
                        throwable -> {
                            //Toast.makeText(getContext(), "Lỗi bài hát " + throwable.getMessage(), Toast.LENGTH_LONG).show();
                        }
                )
        );
    }
}
