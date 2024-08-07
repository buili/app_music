package com.example.music.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class MainViewPageAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> arrayFragment = new ArrayList<>();
    private ArrayList<String> arrayTitle = new ArrayList<>();

    public MainViewPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return arrayFragment.get(position);
    }

    @Override
    public int getCount() {
        return arrayFragment.size();
    }

    public void addFragment(Fragment fragment, String title) {
        arrayFragment.add(fragment);
        arrayTitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayTitle.get(position);
    }
}


//public class MainViewPageAdapter extends FragmentStateAdapter {
//    private ArrayList<Fragment> arrayFragment = new ArrayList<>();
//    private  ArrayList<String> arrayTitle = new ArrayList<>();
//    public MainViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
//        super(fragmentActivity);
//    }
//
//    @NonNull
//    @Override
//    public Fragment createFragment(int position) {
//        return arrayFragment.get(position);
//    }
//
//    @Override
//    public int getItemCount() {
//        return arrayFragment.size();
//    }
//
//    public void addFragment(Fragment fragment, String title){
//        arrayFragment.add(fragment);
//        arrayTitle.add(title);
//    }
//
//
//}
