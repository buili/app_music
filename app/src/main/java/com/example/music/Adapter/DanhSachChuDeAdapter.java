package com.example.music.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.music.Activity.DanhSachTheLoaiTheoChuDeActivity;
import com.example.music.Model.ChuDe;
import com.example.music.R;

import java.util.List;

public class DanhSachChuDeAdapter extends RecyclerView.Adapter<DanhSachChuDeAdapter.ViewHolder> {
    Context context;
    List<ChuDe> chuDeList;

    public DanhSachChuDeAdapter(Context context, List<ChuDe> chuDeList) {
        this.context = context;
        this.chuDeList = chuDeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_cac_chu_de, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChuDe chuDe = chuDeList.get(position);
        Glide.with(context)
                .load(chuDe.getHinhChuDe())
                .placeholder(R.drawable.noanh)
                .error(R.drawable.error)
                .into(holder.imgchude);
    }

    @Override
    public int getItemCount() {
        return chuDeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgchude;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgchude = itemView.findViewById(R.id.imageviewdongcacchude);
            imgchude.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachTheLoaiTheoChuDeActivity.class);
                    ChuDe chuDe = chuDeList.get(getAdapterPosition());
                    intent.putExtra("chude", chuDe);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
