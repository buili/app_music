package com.example.music.Fragment;

import android.database.Observable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.music.Adapter.BannerAdapter;
import com.example.music.Model.QuangCao;
import com.example.music.Model.QuangCaoModel;
import com.example.music.R;
import com.example.music.Retrofit.RetrofitClient;
import com.example.music.Util.ApiMusic;
import com.example.music.Util.Util;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Retrofit;

public class Fragment_Banner extends Fragment {

    View view;

    ViewPager viewPager;
    CircleIndicator circleIndicator;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiMusic apiMusic = RetrofitClient.getInstance(Util.BASE_URL).create(ApiMusic.class);
    List<QuangCao> quangCaoList;

    BannerAdapter bannerAdapter;

    Runnable runnable;
    Handler handler;
    int currentItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner, container, false);
        initView();
        GetData();
        return view;
    }

    private void initView() {
        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.indicator);
    }

    private void GetData() {
        quangCaoList = new ArrayList<>();
        compositeDisposable.add(apiMusic.getsongbanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        quangCaoModel -> {
                            if(quangCaoModel.isSuccess()){
                                quangCaoList = quangCaoModel.getResult();
                                Log.d("LoiBanner", quangCaoList.get(0).getTenBaiHat());
//                                Toast.makeText(getContext(), quangCaoList.get(0).getTenBaiHat(), Toast.LENGTH_LONG).show();

                                bannerAdapter = new BannerAdapter(getActivity(), quangCaoList);
                                viewPager.setAdapter(bannerAdapter);
                                circleIndicator.setViewPager(viewPager);
                                handler = new Handler();
                                runnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        currentItem = viewPager.getCurrentItem();
                                        currentItem++;
                                        if(currentItem >= viewPager.getAdapter().getCount()){
                                            currentItem = 0;
                                        }
                                        viewPager.setCurrentItem(currentItem, true);
                                        handler.postDelayed(runnable, 4500);
                                    }
                                };
                                handler.postDelayed(runnable, 4500);
                            }else{
                                Log.e("LoiBanner", "l");
                            }

                        }, throwable -> {
                            Log.e("LoiBanner", throwable.getMessage());
                           // Toast.makeText(getContext(), "Đã có lỗi lấy quảng cáo" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                        }
                )

        );

    }
}
