package com.example.music.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.music.Activity.DanhSachBaiHatActivity;
import com.example.music.Model.QuangCao;
import com.example.music.R;
import com.example.music.Util.Util;

import java.util.List;

public class BannerAdapter extends PagerAdapter {
    Context context;
    List<QuangCao> list;

    public BannerAdapter(Context context, List<QuangCao> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_banner, container, false);
        ImageView imgbackgroundbanner = view.findViewById(R.id.imageviewbackgroundbanner);
        ImageView imgsongbanner = view.findViewById(R.id.imageviewbanner);
        TextView txttitlesonbanner = view.findViewById(R.id.textviewtitlebannerbaihat);
        TextView txtnoidung = view.findViewById(R.id.textviewnoidung);

        if(list.get(position).getHinhAnh().contains("https")){
            Glide.with(context)
                    .load(list.get(position).getHinhAnh())
                    .placeholder(R.drawable.noanh)
                    .error(R.drawable.error)
                    .into(imgbackgroundbanner);
        }else {
            String hinh1 = Util.BASE_URL + "quangcao/" + list.get(position).getHinhAnh();
            Glide.with(context).load(hinh1)
                    .placeholder(R.drawable.noanh)
                    .error(R.drawable.error)
                    .into(imgbackgroundbanner);
        }
        if(list.get(position).getHinhAnh().contains("https")){
            Glide.with(context)
                    .load(list.get(position).getHinhAnh())
                    .placeholder(R.drawable.noanh)
                    .error(R.drawable.error)
                    .into(imgsongbanner);
        }else {
            String hinh2 = Util.BASE_URL + "anh/" + list.get(position).getHinhAnh();
            Glide.with(context).load(hinh2)
                    .placeholder(R.drawable.noanh)
                    .error(R.drawable.error)
                    .into(imgsongbanner);
        }

        txttitlesonbanner.setText(list.get(position).getTenBaiHat());
        txtnoidung.setText(list.get(position).getNoiDung());


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                QuangCao quangCao = list.get(position);
                intent.putExtra("banner", quangCao);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
              //  Toast.makeText(context, "đ", Toast.LENGTH_LONG).show();
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
