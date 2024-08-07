package com.example.music.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.Model.BaiHat;
import com.example.music.R;

import java.util.List;

public class PlayNhacAdapter extends RecyclerView.Adapter<PlayNhacAdapter.ViewHolder> {
    Context context;
    List<BaiHat> baiHatList;

    public PlayNhacAdapter(Context context, List<BaiHat> baiHatList) {
        this.context = context;
        this.baiHatList = baiHatList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_play_bai_hat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = baiHatList.get(position);
        holder.txttenbaihat.setText(baiHat.getTenBaiHat());
        holder.txtcasi.setText(baiHat.getCaSi());
        holder.txtindex.setText(position + 1 + "");
    }

    @Override
    public int getItemCount() {
        return baiHatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtindex, txttenbaihat, txtcasi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtindex = itemView.findViewById(R.id.textviewplaynhacindex);
            txtcasi = itemView.findViewById(R.id.textviewplaynhactencasi);
            txttenbaihat = itemView.findViewById(R.id.textviewplaytenbaihat);
        }
    }
}
