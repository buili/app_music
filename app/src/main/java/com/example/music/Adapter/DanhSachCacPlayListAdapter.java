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
import com.example.music.Model.Playlist;
import com.example.music.R;

import java.util.List;

public class DanhSachCacPlayListAdapter extends RecyclerView.Adapter<DanhSachCacPlayListAdapter.ViewHolder> {
    Context context;
    List<Playlist> playlistList;

    public DanhSachCacPlayListAdapter(Context context, List<Playlist> playlistList) {
        this.context = context;
        this.playlistList = playlistList;
    }

    @NonNull
    @Override
    public DanhSachCacPlayListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_danh_sach_cac_playlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhSachCacPlayListAdapter.ViewHolder holder, int position) {
        Playlist playlist = playlistList.get(position);
        holder.txttenplaylist.setText(playlist.getTen());
        Glide.with(context)
                .load(playlist.getHinhAnh())
                .placeholder(R.drawable.noanh)
                .error(R.drawable.error)
                .into(holder.imghinhnen);
    }

    @Override
    public int getItemCount() {
        return playlistList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imghinhnen;
        TextView txttenplaylist;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhnen = itemView.findViewById(R.id.imageviewdanhsachcacplaylist);
            txttenplaylist = itemView.findViewById(R.id.textviewtendanhsachcacplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    Playlist playlist = playlistList.get(getAdapterPosition());
                    intent.putExtra("itemplaylist", playlist);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
