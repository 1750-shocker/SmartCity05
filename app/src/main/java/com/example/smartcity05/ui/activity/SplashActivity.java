package com.example.smartcity05.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import androidx.annotation.Nullable;

import com.example.smartcity05.MainActivity;
import com.example.smartcity05.databinding.ActivitySplashBinding;
import com.example.smartcity05.util.BaseActivity;
import com.example.smartcity05.util.Constant;
import com.example.smartcity05.util.SPUtil;


public class SplashActivity extends BaseActivity {
    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AlphaAnimation animation = new AlphaAnimation(0.5f, 1.0f);
        animation.setDuration(2000);
        binding.imageView.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                jump2Activity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void jump2Activity() {
        //因为是傻逼比赛，所以默认一定进去了就会登录，少写个逻辑
        Intent intent = new Intent();
        if (SPUtil.getBoolean(Constant.IS_FIRST, true)) {
            intent.setClass(this, GuideActivity.class);
        } else if (SPUtil.getBoolean(Constant.IS_LOG_IN, false)) {
            intent.setClass(this, MainActivity.class);
        } else {
            intent.setClass(this, LoginActivity.class);
        }
        startActivity(intent);
        finish();
    }
}