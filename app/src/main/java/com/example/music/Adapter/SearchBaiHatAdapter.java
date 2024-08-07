package com.example.music.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.music.Activity.PlayNhacActivity;
import com.example.music.Model.BaiHat;
import com.example.music.R;
import com.example.music.Retrofit.RetrofitClient;
import com.example.music.Util.ApiMusic;
import com.example.music.Util.Util;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchBaiHatAdapter extends RecyclerView.Adapter<SearchBaiHatAdapter.ViewHolder> {
    Context context;
    List<BaiHat> baiHatList;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiMusic apiMusic = RetrofitClient.getInstance(Util.BASE_URL).create(ApiMusic.class);

    public SearchBaiHatAdapter(Context context, List<BaiHat> baiHatList) {
        this.context = context;
        this.baiHatList = baiHatList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_search_bai_hat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = baiHatList.get(position);
        holder.txtTenbaihat.setText(baiHat.getTenBaiHat());
        holder.txtCasi.setText(baiHat.getCaSi());
        Glide.with(context)
                .load(baiHat.getHinhBaiHat())
                .placeholder(R.drawable.noanh)
                .error(R.drawable.error)
                .into(holder.imgbaihat);
    }

    @Override
    public int getItemCount() {
        return baiHatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenbaihat, txtCasi;
        ImageView imgbaihat, imgluotthich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenbaihat = itemView.findViewById(R.id.textviewsearchtenbaihat);
            txtCasi = itemView.findViewById(R.id.textviewsearchtencasi);
            imgbaihat = itemView.findViewById(R.id.imageviewsearchbaihat);
            imgluotthich = itemView.findViewById(R.id.imageviewSearchluotthich);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", baiHatList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });

            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgluotthich.setImageResource(R.drawable.love_selected_24);
                    updateLuotThich();
                }
            });

        }

        private void updateLuotThich() {
            compositeDisposable.add(apiMusic.updateLuotThich(1, baiHatList.get(getAdapterPosition()).getIdBaiHat())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            updateModel -> {
                                if (updateModel.isSuccess()) {
                                    Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
                                }
                            },
                            throwable -> {
                                Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                            }
                    )
            );
            imgluotthich.setEnabled(false);
        }


    }


}
