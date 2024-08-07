package com.example.music.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.music.Activity.DanhSachBaiHatActivity;
import com.example.music.Activity.DanhSachTatCaChuDeActivity;
import com.example.music.Model.ChuDe;
import com.example.music.Model.ChuDe_TheLoai;
import com.example.music.Model.Playlist;
import com.example.music.Model.TheLoai;
import com.example.music.R;
import com.example.music.Retrofit.RetrofitClient;
import com.example.music.Util.ApiMusic;
import com.example.music.Util.Util;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Fragment_ChuDe_TheLoai_Today extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtxemthemchudetheloai;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiMusic apiMusic;

    ChuDe_TheLoai chuDeTheLoaiList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai_today, container, false);
        initView();
        getData();
        initControl();
        return view;
    }

    private void initControl() {
        txtxemthemchudetheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachTatCaChuDeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        compositeDisposable.add(apiMusic.getChudeTheloai()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        chuDeTheLoaiModel -> {
                            if (chuDeTheLoaiModel.isSuccess()) {
                                chuDeTheLoaiList = chuDeTheLoaiModel.getResult();

                                Log.d("Fragment", "Data received: " + chuDeTheLoaiList.toString());

                                List<ChuDe> chuDeList = chuDeTheLoaiList.getChuDe();
                                List<TheLoai> theLoaiList = chuDeTheLoaiList.getTheLoai();

                                if (chuDeList == null) {
                                    chuDeList = new ArrayList<>();
                                }
                                if (theLoaiList == null) {
                                    theLoaiList = new ArrayList<>();
                                }

                                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>(chuDeList);
                                final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>(theLoaiList);

                                Log.d("Fragment", "ChuDeArrayList size: " + chuDeArrayList.size());
                                Log.d("Fragment", "TheLoaiArrayList size: " + theLoaiArrayList.size());

                                LinearLayout linearLayout = new LinearLayout(getActivity());
                                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(580, 250);
                                layoutParams.setMargins(10, 20, 10, 30);

                                for (ChuDe chuDe : chuDeArrayList) {
                                    CardView cardView = new CardView(getActivity());
                                    cardView.setRadius(10);
                                    ImageView imageView = new ImageView(getActivity());
                                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                    if (chuDe.getHinhChuDe() != null){
                                        Glide.with(getActivity())
                                                .load(chuDe.getHinhChuDe())
                                                .error(R.drawable.error)
                                                .placeholder(R.drawable.noanh)
                                                .into(imageView);
                                    }
                                    cardView.setLayoutParams(layoutParams);
                                    cardView.addView(imageView);
                                    linearLayout.addView(cardView);

                                    imageView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(getActivity(), DanhSachBaiHatActivity.class);
                                            intent.putExtra("chude", chuDe);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                        }
                                    });

                                    Log.d("Fragment", "CardView added to linearLayout for ChuDe: " + chuDe.getHinhChuDe());
                                }

                                for(TheLoai theLoai : theLoaiArrayList){
                                    CardView cardView = new CardView(getActivity());
                                    cardView.setRadius(10);
                                    ImageView imageView = new ImageView(getActivity());
                                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                    if(theLoai.getHinhTheLoai() != null){
                                        Glide.with(getActivity())
                                                .load(theLoai.getHinhTheLoai())
                                                .error(R.drawable.error)
                                                .placeholder(R.drawable.noanh)
                                                .into(imageView);
                                    }
                                    cardView.setLayoutParams(layoutParams);
                                    cardView.addView(imageView);
                                    linearLayout.addView(cardView);
                                    imageView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(getActivity(), DanhSachBaiHatActivity.class);
                                            intent.putExtra("idtheloai", theLoai);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                        }
                                    });

                                    Log.d("Fragment", "CardView added to linearLayout for TheLoai: " + theLoai.getHinhTheLoai());
                                }

                                horizontalScrollView.removeAllViews();
                                horizontalScrollView.addView(linearLayout);
                            }
                        },
                        throwable -> {
                            Log.e("Chude", throwable.getMessage());
                           // Toast.makeText(getContext(), "Lỗi khi lấy cd, tl" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                        }
                )
        );
    }

    private void initView() {
        apiMusic = RetrofitClient.getInstance(Util.BASE_URL).create(ApiMusic.class);
        horizontalScrollView = view.findViewById(R.id.horizontalScrollview);
        txtxemthemchudetheloai = view.findViewById(R.id.textviewxemthem);

        Log.d("Fragment", "horizontalScrollView initialized: " + (horizontalScrollView != null));
    }
}