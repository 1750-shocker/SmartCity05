package com.example.smartcity05.ui.activity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity05.R;
import com.example.smartcity05.bean.MsgBean;
import com.example.smartcity05.databinding.ActivityFeedBackBinding;
import com.example.smartcity05.net.NetWorkApi;
import com.example.smartcity05.net.RetroApi;
import com.example.smartcity05.util.BaseActivity;
import com.example.smartcity05.util.ViewUtil;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedBackActivity extends BaseActivity {
    private ActivityFeedBackBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedBackBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setToolbar(binding.toolbar, "意见反馈");
        binding.etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.tvChangedNum.setText(editable.length()+"");
            }
        });
        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewUtil.hideSoftKeyboard(FeedBackActivity.this, binding.btnSend);
                String content = binding.etContent.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    showToast("请输入您的建议");
                    return;
                }
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("content", binding.etContent.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody requestBody = NetWorkApi.getRequestBody(jsonObject);
                NetWorkApi.createService(RetroApi.class).getFeedBackBean(requestBody).enqueue(new Callback<MsgBean>() {
                    @Override
                    public void onResponse(Call<MsgBean> call, Response<MsgBean> response) {
                        MsgBean body = response.body();
                        Toast.makeText(FeedBackActivity.this, body.getCode() + body.getMsg(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<MsgBean> call, Throwable throwable) {
                        Toast.makeText(FeedBackActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}