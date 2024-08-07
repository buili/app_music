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

public class BaiHatHotAdapter extends RecyclerView.Adapter<BaiHatHotAdapter.ViewHolder> {

   Context context;
   List<BaiHat> baiHatList;

   CompositeDisposable compositeDisposable = new CompositeDisposable();
   ApiMusic apiMusic;

    public BaiHatHotAdapter(Context context, List<BaiHat> baiHatList) {
        this.context = context;
        this.baiHatList = baiHatList;
        apiMusic = RetrofitClient.getInstance(Util.BASE_URL).create(ApiMusic.class);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_bai_hat_hot, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = baiHatList.get(position);
        holder.txtcasi.setText(baiHat.getCaSi());
        holder.txtten.setText(baiHat.getTenBaiHat());
        Glide.with(context)
                .load(baiHat.getHinhBaiHat())
                .placeholder(R.drawable.noanh)
                .error(R.drawable.error)
                .into(holder.imghinh);

    }

    @Override
    public int getItemCount() {
        return baiHatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtten, txtcasi;
        ImageView imghinh, imgluotthich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtten = itemView.findViewById(R.id.textviewtenbaihathot);
            txtcasi = itemView.findViewById(R.id.textviewcasibaihathot);
            imghinh = itemView.findViewById(R.id.imageviewbaihathot);
            imgluotthich = itemView.findViewById(R.id.imageviewluotthich);
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgluotthich.setImageResource(R.drawable.love_selected_24);
                    updateLuotThich();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    BaiHat baiHat = baiHatList.get(getAdapterPosition());
                    intent.putExtra("cakhuc", baiHat);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
        private void updateLuotThich() {
            compositeDisposable.add(apiMusic.updateLuotThich(1, baiHatList.get(getAdapterPosition()).getIdBaiHat())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            updateModel -> {
                                if(updateModel.isSuccess()){
                                    Toast.makeText(context, "Đã Thích", Toast.LENGTH_SHORT).show();
                                }
                            },
                            throwable -> {

                            }
                    )
            );
            imgluotthich.setEnabled(false);
        }
    }


}
