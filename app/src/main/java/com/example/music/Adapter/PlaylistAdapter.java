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
import com.example.music.Interface.ItemClickListener;
import com.example.music.Model.BaiHat;
import com.example.music.Model.Playlist;
import com.example.music.R;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {
   Context context;
   List<Playlist> playlistList;

    public PlaylistAdapter(Context context, List<Playlist> playlistList) {
        this.context = context;
        this.playlistList = playlistList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dongplaylist,parent, false);
        return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = playlistList.get(position);
        holder.txttenplaylist.setText(playlist.getTen());
        Glide.with(context)
                .load(playlist.getHinhAnh())
                .placeholder(R.drawable.noanh)
                .error(R.drawable.error)
                .into(holder.imgbackground);
        Glide.with(context)
                .load(playlist.getHinhIcon())
                .placeholder(R.drawable.noanh)
                .error(R.drawable.error)
                .into(holder.imgplaylist);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int pos, boolean isLongClick) {
                if(!isLongClick){
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    Playlist playlist1 = playlistList.get(pos);
                    intent.putExtra("itemplaylist", playlist1);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return playlistList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgbackground, imgplaylist;
        TextView txttenplaylist;
        ItemClickListener itemClickListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgbackground = itemView.findViewById(R.id.imageviewbackgroundplaylist);
            imgplaylist = itemView.findViewById(R.id.imageviewplaylist);
            txttenplaylist = itemView.findViewById(R.id.textviewtenplaylist);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }
    }
}
