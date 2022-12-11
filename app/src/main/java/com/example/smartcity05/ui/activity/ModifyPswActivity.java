package com.example.smartcity05.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.smartcity05.R;
import com.example.smartcity05.bean.MsgBean;
import com.example.smartcity05.databinding.ActivityModifyPswBinding;
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

public class ModifyPswActivity extends BaseActivity {
    private ActivityModifyPswBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityModifyPswBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setToolbar(binding.toolbar,"修改密码");
        binding.btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewUtil.hideSoftKeyboard(ModifyPswActivity.this, binding.etNewPsw);
                String oldPsw = binding.etOldPsw.getText().toString().trim();
                if (TextUtils.isEmpty(oldPsw)) {
                    showToast("旧密码");
                    return;
                }

                String newPsw = binding.etNewPsw.getText().toString().trim();
                if (TextUtils.isEmpty(newPsw)) {
                    showToast("新密码");
                    return;
                }

                // TODO validate success, do something
                if (oldPsw.equals(newPsw)) {
                    showToast("新密码与旧密码一致");
                    return;
                }
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("newPassword", newPsw);
                    jsonObject.put("oldPassword", oldPsw);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody requestBody = NetWorkApi.getRequestBody(jsonObject);
                NetWorkApi.createService(RetroApi.class).getModifyPswBean(requestBody).enqueue(new Callback<MsgBean>() {
                    @Override
                    public void onResponse(Call<MsgBean> call, Response<MsgBean> response) {
                        MsgBean body = response.body();
                        showToast(body.getMsg());
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