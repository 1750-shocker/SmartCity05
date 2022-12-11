package com.example.smartcity05.ui.bottomFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartcity05.databinding.FragmentNewslistBinding;
import com.example.smartcity05.ui.adapter.NewsTabAdapter;

public class NewsListFragment extends Fragment {

    private FragmentNewslistBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewslistBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] titles = {"今日要闻", "专题聚焦", "政策解读", "经济发展", "文化旅游", "科技创新"};
        binding.vp.setAdapter(new NewsTabAdapter(getChildFragmentManager(), titles));
        binding.tab.setupWithViewPager(binding.vp);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
