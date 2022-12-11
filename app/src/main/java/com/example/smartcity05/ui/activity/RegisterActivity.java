package com.example.smartcity05.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;

import com.example.smartcity05.R;
import com.example.smartcity05.bean.RegisterBean;
import com.example.smartcity05.databinding.ActivityRegisterBinding;
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

public class RegisterActivity extends BaseActivity {
    private ActivityRegisterBinding binding;
    private String sex = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setToolbar(binding.toolbar, "用户注册");
        binding.radioGroup.check(binding.radioButtonMan.getId());
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton_man) {
                    sex = "0";
                } else if (checkedId == R.id.radioButton_woman) {
                    sex = "1";
                }
            }
        });
        binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewUtil.hideSoftKeyboard(RegisterActivity.this, binding.buttonRegister);
                String userName = binding.editTextTextPersonName.getText().toString().trim();
                String password = binding.editTextTextPassword.getText().toString().trim();
                String phonenumber = binding.editTextPhone.getText().toString().trim();
                if (TextUtils.isEmpty(userName)) {
                    showToast("请填写用户名");
                } else if (TextUtils.isEmpty(password)) {
                    showToast("请填写密码");
                } else if (TextUtils.isEmpty(phonenumber)) {
                    showToast("请填写电话号码");
                } else {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("userName", userName);
                        jsonObject.put("password", password);
                        jsonObject.put("phonenumber", phonenumber);
                        jsonObject.put("sex", sex);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RequestBody requestBody = NetWorkApi.getRequestBody(jsonObject);
                    NetWorkApi.createService(RetroApi.class).getRegisterBean(requestBody).enqueue(new Callback<RegisterBean>() {
                        @Override
                        public void onResponse(Call<RegisterBean> call, Response<RegisterBean> response) {
                            RegisterBean body = response.body();
                            if (body.getCode() == 200) {
                                showToast(body.getMsg());
                                finish();
                            } else {
                                showToast(body.getCode() + " " + body.getMsg());
                            }
                        }

                        @Override
                        public void onFailure(Call<RegisterBean> call, Throwable throwable) {
                            showToast(throwable.getMessage());
                        }
                    });
                }
            }
        });
    }
}