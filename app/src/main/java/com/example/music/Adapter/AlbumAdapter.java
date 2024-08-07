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

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    Context context;
    List<Album> albumList;

    public AlbumAdapter(Context context, List<Album> albumList) {
        this.context = context;
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_album, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.txtcasialbum.setText(album.getTenCaSiAlbum());
        holder.txttenalbum.setText(album.getTenAlbum());
        Glide.with(context)
                .load(album.getHinhAlbum())
                .placeholder(R.drawable.noanh)
                .error(R.drawable.error)
                .into(holder.imghinhalbum);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imghinhalbum;
        TextView txttenalbum, txtcasialbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhalbum = itemView.findViewById(R.id.imageviewalbum);
            txttenalbum = itemView.findViewById(R.id.textviewtenalbum);
            txtcasialbum = itemView.findViewById(R.id.textviewtencasialbum);
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
