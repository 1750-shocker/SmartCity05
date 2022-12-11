package com.example.smartcity05.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Binder;
import android.os.Bundle;

import com.example.smartcity05.R;
import com.example.smartcity05.bean.OrderListBean;
import com.example.smartcity05.databinding.ActivityOrderListBinding;
import com.example.smartcity05.net.NetWorkApi;
import com.example.smartcity05.net.RetroApi;
import com.example.smartcity05.ui.adapter.OrderListAdapter;
import com.example.smartcity05.util.BaseActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderListActivity extends BaseActivity {
    private ActivityOrderListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setToolbar(binding.toolbar, "订单");
        binding.recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        NetWorkApi.createService(RetroApi.class).getOrderListBean().enqueue(new Callback<OrderListBean>() {
            @Override
            public void onResponse(Call<OrderListBean> call, Response<OrderListBean> response) {
                OrderListBean body = response.body();
                if (body.getCode() == 200) {
                    List<OrderListBean.RowsBean> rows = body.getRows();
                    binding.recycler.setAdapter(new OrderListAdapter(OrderListActivity.this, rows));
                } else {
                    showToast(body.getMsg());
                }
            }

            @Override
            public void onFailure(Call<OrderListBean> call, Throwable throwable) {
                showToast(throwable.getMessage());
            }
        });
    }
}