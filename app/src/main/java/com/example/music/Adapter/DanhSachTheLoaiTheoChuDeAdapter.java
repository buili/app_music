package com.example.music.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.music.Activity.DanhSachBaiHatActivity;
import com.example.music.Model.TheLoai;
import com.example.music.R;

import java.util.List;

public class DanhSachTheLoaiTheoChuDeAdapter extends RecyclerView.Adapter<DanhSachTheLoaiTheoChuDeAdapter.ViewHolder> {
    Context context;
    List<TheLoai> theLoaiList;

    public DanhSachTheLoaiTheoChuDeAdapter(Context context, List<TheLoai> theLoaiList) {
        this.context = context;
        this.theLoaiList = theLoaiList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_the_loai_theo_chu_de, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai = theLoaiList.get(position);
        holder.txttentheloai.setText(theLoai.getTenTheLoai());
        Glide.with(context)
                .load(theLoai.getHinhTheLoai())
                .placeholder(R.drawable.noanh)
                .error(R.drawable.error)
                .into(holder.imghinhnen);
    }

    @Override
    public int getItemCount() {
        return theLoaiList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imghinhnen;
        TextView txttentheloai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhnen = itemView.findViewById(R.id.imageviewtheloaitheochude);
            txttentheloai = itemView.findViewById(R.id.textviewtentheloaitheochude);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    TheLoai theLoai = theLoaiList.get(getAdapterPosition());
                    intent.putExtra("idtheloai", theLoai);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
