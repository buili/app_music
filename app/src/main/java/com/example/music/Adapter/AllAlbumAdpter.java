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
import com.example.music.Model.Album;
import com.example.music.R;

import java.util.List;

public class AllAlbumAdpter extends RecyclerView.Adapter<AllAlbumAdpter.ViewHolder> {
    Context context;
    List<Album> albumList;

    public AllAlbumAdpter(Context context, List<Album> albumList) {
        this.context = context;
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_all_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.txttenallalbum.setText(album.getTenAlbum());
        Glide.with(context)
                .load(album.getHinhAlbum())
                .placeholder(R.drawable.noanh)
                .error(R.drawable.error)
                .into(holder.imgallalbum);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgallalbum;
        TextView txttenallalbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgallalbum = itemView.findViewById(R.id.imageviewallalbum);
            txttenallalbum = itemView.findViewById(R.id.textviewtenallalbum);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    Album album = albumList.get(getAdapterPosition());
                    intent.putExtra("album", album);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
