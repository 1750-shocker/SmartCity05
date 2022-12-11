package com.example.smartcity05.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.smartcity05.ui.fragment.NewsVpFragment;

public class NewsTabAdapter extends FragmentPagerAdapter {
    private String[] titles;

    public NewsTabAdapter(@NonNull FragmentManager fm,String[] titles) {
        super(fm);
        this.titles = titles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return NewsVpFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
