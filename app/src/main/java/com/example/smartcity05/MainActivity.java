package com.example.smartcity05;

import android.os.Bundle;
import android.widget.Toast;

import com.example.smartcity05.bean.NewsBean;
import com.example.smartcity05.bean.NewsListBean;
import com.example.smartcity05.net.NetWorkApi;
import com.example.smartcity05.util.BaseActivity;
import com.example.smartcity05.util.Constant;
import com.example.smartcity05.util.MDBHelper;
import com.example.smartcity05.net.RetroApi;
import com.example.smartcity05.util.SPUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.j256.ormlite.dao.Dao;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import java.sql.SQLException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        netRequest();
        SPUtil.putString(Constant.IP_PORT, "http://124.93.196.45:10001");
    }

    private void netRequest() {
        //网络请求新闻资源并存储在ormlite
        NetWorkApi.createService(RetroApi.class).getNewsListBean().enqueue(new Callback<NewsListBean>() {
            @Override
            public void onResponse(Call<NewsListBean> call, Response<NewsListBean> response) {
                NewsListBean body = response.body();
                if (body.getCode() == 200) {
                    List<NewsBean> rows = body.getRows();
                    try {
                        Dao<NewsBean, Integer> dao = MDBHelper.getInstance(MainActivity.this).getNewsBeanDao();
                        dao.delete(dao.queryForAll());
                        dao.create(rows);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(MainActivity.this, body.getCode() + body.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewsListBean> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}