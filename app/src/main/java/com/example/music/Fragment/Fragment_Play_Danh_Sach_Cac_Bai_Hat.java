package com.example.music.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.Activity.PlayNhacActivity;
import com.example.music.Adapter.PlayNhacAdapter;
import com.example.music.R;

public class Fragment_Play_Danh_Sach_Cac_Bai_Hat extends Fragment {
    View view;
    RecyclerView recyclerView;
    PlayNhacAdapter playNhacAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_danh_sach_cac_bai_hat, container, false);
        initView();
        return view;
    }

    private void initView() {
        recyclerView = view.findViewById(R.id.recyclerviewPlaybaihat);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        if(PlayNhacActivity.baiHatList.size() > 0){
            playNhacAdapter = new PlayNhacAdapter(getActivity(), PlayNhacActivity.baiHatList);
            recyclerView.setAdapter(playNhacAdapter);
        }
    }
}
