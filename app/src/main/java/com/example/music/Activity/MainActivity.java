package com.example.music.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.music.Adapter.MainViewPageAdapter;
import com.example.music.Fragment.Fragment_Tim_Kiem;
import com.example.music.Fragment.Fragment_Trang_Chu;
import com.example.music.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tabLayout = findViewById(R.id.myTablLayout);
        viewPager = findViewById(R.id.myViewPager);

        MainViewPageAdapter mainViewPageAdapter = new MainViewPageAdapter(getSupportFragmentManager());
        mainViewPageAdapter.addFragment(new Fragment_Trang_Chu(), "Trang Chủ");
        mainViewPageAdapter.addFragment(new Fragment_Tim_Kiem(), "Tìm Kiếm");
        viewPager.setAdapter(mainViewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.baseline_home_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.baseline_search_24);
    }
}