package com.example.music.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.Adapter.SearchBaiHatAdapter;
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


public class Fragment_Tim_Kiem extends Fragment {
    View view;
    Toolbar toolbar;
    RecyclerView recyclerView;
    TextView txtkhongcodulieu;

    SearchBaiHatAdapter searchBaiHatAdapter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiMusic apiMusic;

    List<BaiHat> baiHatList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tim_kiem, container, false);
        initView();
        actionBar();

        return view;
    }

    private void searchBaiHat(String s) {
        compositeDisposable.add(apiMusic.getSearchBaiHat(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        baiHatModel -> {
                            if(baiHatModel.isSuccess()){
                                baiHatList = baiHatModel.getResult();
                                if(baiHatList.size() > 0){
                                    searchBaiHatAdapter = new SearchBaiHatAdapter(getActivity(), baiHatList);
                                    recyclerView.setAdapter(searchBaiHatAdapter);
                                    txtkhongcodulieu.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                }else{
                                    txtkhongcodulieu.setVisibility(View.VISIBLE);
                                    recyclerView.setVisibility(View.GONE);
                                }
                            }
                        },
                        throwable -> {

                        }
                )
        );
    }

    private void actionBar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    private void initView() {
        toolbar = view.findViewById(R.id.toolbarsearchbaihat);
        recyclerView = view.findViewById(R.id.recyclerviewsearchbaihat);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        txtkhongcodulieu = view.findViewById(R.id.textviewkhongcodulieu);

        apiMusic = RetrofitClient.getInstance(Util.BASE_URL).create(ApiMusic.class);
        setHasOptionsMenu(true);

        baiHatList = new ArrayList<>();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_view, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchBaiHat(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }
}
