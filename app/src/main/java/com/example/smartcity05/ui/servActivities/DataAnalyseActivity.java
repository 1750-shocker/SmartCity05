package com.example.smartcity05.ui.servActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.smartcity05.databinding.ActivityDataAnalyseBinding;


public class DataAnalyseActivity extends AppCompatActivity {
    private ActivityDataAnalyseBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataAnalyseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}