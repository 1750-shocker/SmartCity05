package com.example.smartcity05.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;


import androidx.annotation.Nullable;

import com.example.smartcity05.MainActivity;
import com.example.smartcity05.bean.LoginBean;
import com.example.smartcity05.databinding.ActivityLoginBinding;
import com.example.smartcity05.net.NetWorkApi;
import com.example.smartcity05.net.RetroApi;
import com.example.smartcity05.util.BaseActivity;
import com.example.smartcity05.util.Constant;
import com.example.smartcity05.util.SPUtil;
import com.example.smartcity05.util.ViewUtil;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {
    private ActivityLoginBinding binding;
    private String username;
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = binding.etUsername.getText().toString().trim();
                password = binding.etPassword.getText().toString().trim();
                if (TextUtils.isEmpty(username)) {
                    showToast("未填写用户名");
                } else if (TextUtils.isEmpty(password)) {
                    showToast("未填写密码");
                } else {
                    ViewUtil.hideSoftKeyboard(LoginActivity.this, binding.textView);
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("username", username);
                        jsonObject.put("password", password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RequestBody requestBody = NetWorkApi.getRequestBody(jsonObject);
                    //通过框架给每个View提供的Presenter来进行数据操作（网络，数据库）
                    NetWorkApi.createServiceWithoutClient(RetroApi.class).getLoginBean(requestBody).enqueue(new Callback<LoginBean>() {
                        @Override
                        public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                            LoginBean body = response.body();
                            if (body.getCode() == 200) {
                                showToast(body.getMsg());
                                SPUtil.putString(Constant.TOKEN, body.getToken());
                                SPUtil.putBoolean(Constant.IS_LOG_IN, true);
                                SPUtil.putString(Constant.USER_NAME, username);
                                SPUtil.putString(Constant.PASSWORD, password);
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            } else {
                                showToast(body.getCode() + " " + body.getMsg());
                            }
                        }


                        @Override
                        public void onFailure(Call<LoginBean> call, Throwable throwable) {
                            showToast(throwable.getMessage());
                        }
                    });
                }

            }
        });
        binding.etUsername.setText(SPUtil.getString(Constant.USER_NAME, ""));
        binding.etPassword.setText(SPUtil.getString(Constant.PASSWORD, ""));
        binding.textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }


}