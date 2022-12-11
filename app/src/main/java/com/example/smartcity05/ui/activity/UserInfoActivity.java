package com.example.smartcity05.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.smartcity05.R;
import com.example.smartcity05.bean.MsgBean;
import com.example.smartcity05.bean.UserInfoBean;
import com.example.smartcity05.databinding.ActivityUserInfoBinding;
import com.example.smartcity05.net.NetWorkApi;
import com.example.smartcity05.net.RetroApi;
import com.example.smartcity05.util.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInfoActivity extends BaseActivity {
    private ActivityUserInfoBinding binding;
    private String sex = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setToolbar(binding.toolbar, "修改个人信息");
        binding.rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (binding.rbMan.isChecked()) {
                    sex = "0";
                } else {
                    sex = "1";
                }
            }
        });
        NetWorkApi.createService(RetroApi.class).getUserInfoBean().enqueue(new Callback<UserInfoBean>() {
            @Override
            public void onResponse(Call<UserInfoBean> call, Response<UserInfoBean> response) {
                UserInfoBean body = response.body();
                if (body.getCode() == 200) {
                    UserInfoBean.UserBean user = body.getUser();
                    binding.etNickname.setText(user.getNickName());
                    binding.etEmail.setText(user.getEmail());
                    String idCard = user.getIdCard();
                    if (!TextUtils.isEmpty(idCard)) {
                        binding.etIdcard.setText(idCard.substring(0, 2) + "*********" + idCard.substring(idCard.length() - 4, idCard.length()));
                    } else {
                        binding.etIdcard.setText("");
                    }
                    binding.etPhonenumber.setText(user.getPhonenumber());
                    sex = user.getSex();
                    binding.rgSex.check(sex.equals("1") ? R.id.rb_woman : R.id.rb_man);
                } else {
                    Toast.makeText(UserInfoActivity.this, body.getCode() + " " + body.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserInfoBean> call, Throwable throwable) {
                Toast.makeText(UserInfoActivity.this, "请检查网络连接", Toast.LENGTH_SHORT).show();
            }
        });
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nickname = binding.etNickname.getText().toString().trim();
                if (TextUtils.isEmpty(nickname)) {
                    showToast("昵称");
                    return;
                }

                String phonenumber = binding.etPhonenumber.getText().toString().trim();
                if (TextUtils.isEmpty(phonenumber)) {
                    showToast("电话号码");
                    return;
                }

                String email = binding.etEmail.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    showToast("邮箱地址");
                    return;
                }

                String idcard = binding.etIdcard.getText().toString().trim();
                if (TextUtils.isEmpty(idcard)) {
                    showToast("身份证号码");
                    return;
                }

                final JSONObject object = new JSONObject();
                try {
                    object.put("nickName", nickname);
                    object.put("phonenumber", phonenumber);
                    object.put("email", email);
                    object.put("idCard", idcard);
                    object.put("sex", sex);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                final RequestBody requestBody = NetWorkApi.getRequestBody(object);
                NetWorkApi.createService(RetroApi.class).getChangeUserInfoBean(requestBody).enqueue(new Callback<MsgBean>() {
                    @Override
                    public void onResponse(Call<MsgBean> call, Response<MsgBean> response) {
                        MsgBean body = response.body();
                        showToast(body.getCode() + " " + body.getMsg());
                    }

                    @Override
                    public void onFailure(Call<MsgBean> call, Throwable throwable) {
                        showToast(throwable.getMessage());
                    }
                });
            }
        });
    }
}