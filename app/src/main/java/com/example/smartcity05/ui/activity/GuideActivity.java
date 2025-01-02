package com.example.smartcity05.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.smartcity05.databinding.ActivityGuideBinding;
import com.example.smartcity05.ui.adapter.GuideAdapter;
//import com.example.smcity.databinding.ActivityGuideBinding;

public class GuideActivity extends AppCompatActivity {
    private ActivityGuideBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGuideBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.viewPager.setAdapter(new GuideAdapter(this));
        binding.viewPager.setCurrentItem(0);
    }
}