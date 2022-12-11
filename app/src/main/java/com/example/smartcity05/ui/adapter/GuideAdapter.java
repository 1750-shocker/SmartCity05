package com.example.smartcity05.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


import com.example.smartcity05.R;
import com.example.smartcity05.databinding.ItemGuideBinding;
import com.example.smartcity05.ui.activity.GuideActivity;
import com.example.smartcity05.ui.activity.LoginActivity;
import com.example.smartcity05.util.Constant;
import com.example.smartcity05.util.SPUtil;

import java.util.ArrayList;
import java.util.List;

public class GuideAdapter extends PagerAdapter {
    private Context context;
    private int[] imgs = {R.drawable.y1, R.drawable.y2, R.drawable.y3, R.drawable.y4};
    private List<View> views = new ArrayList<>();
    private ItemGuideBinding binding;

    public GuideAdapter(final Context context) {

        this.context = context;
        for (int i = 0; i < imgs.length; i++) {
            binding = ItemGuideBinding.inflate(LayoutInflater.from(context), null, false);
            binding.imageView.setImageResource(imgs[i]);
            for (int j = 0; j < imgs.length; j++) {
                RadioButton radioButton = new RadioButton(context);
                radioButton.setLayoutParams(new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                radioButton.setButtonDrawable(R.drawable.selector_circle_indicator);
                radioButton.setPadding(10, 10, 10, 10);
                binding.radioGroup.addView(radioButton);
            }
            ((RadioButton) (binding.radioGroup.getChildAt(i))).setChecked(true);
            if (i == imgs.length - 1) {
                binding.button.setVisibility(View.VISIBLE);
                binding.llInputip.setVisibility(View.VISIBLE);
                binding.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ip = binding.etGuideIp.getText().toString().trim();
                        String port = binding.etGuidePort.getText().toString().trim();
                       /* if (TextUtils.isEmpty(ip) || TextUtils.isEmpty(port)) {
                            Toast.makeText(context, "请设置服务器地址", Toast.LENGTH_SHORT).show();
                        } else {
                            SPUtil.putBoolean(Constant.IS_FIRST, false);
                            SPUtil.putString(Constant.IP_PORT, "http://" + ip + ":" + port);
                            context.startActivity(new Intent(context, LoginActivity.class));
                            ((GuideActivity) context).finish();
                        }*/
                        SPUtil.putBoolean(Constant.IS_FIRST, false);
                        SPUtil.putString(Constant.IP_PORT, "http://" + ip + ":" + port);
                        context.startActivity(new Intent(context, LoginActivity.class));
                        ((GuideActivity) context).finish();
                    }
                });
            }
            views.add(binding.getRoot());
        }

    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(views.get(position));
    }
}
