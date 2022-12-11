package com.example.smartcity05.ui.bottomFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.smartcity05.R;
import com.example.smartcity05.bean.BannerBean;
import com.example.smartcity05.bean.ServBean;
import com.example.smartcity05.databinding.FragmentHomeBinding;
import com.example.smartcity05.net.NetWorkApi;
import com.example.smartcity05.ui.activity.NewsDetailActivity;
import com.example.smartcity05.ui.adapter.ServiceGridAdapter;
import com.example.smartcity05.util.Constant;
import com.example.smartcity05.net.RetroApi;
import com.example.smartcity05.util.SPUtil;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.rvGrid.setOverScrollMode(View.OVER_SCROLL_NEVER);
        binding.rvGrid.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        List<ServBean> rows = new ArrayList<>();
        rows.add(new ServBean(1,"智慧巴士", R.mipmap.bus));
        rows.add(new ServBean(1,"门诊预约", R.mipmap.hospital));
        rows.add(new ServBean(1,"外卖订餐", R.mipmap.waimai));
        rows.add(new ServBean(1,"找房子", R.mipmap.house));
        rows.add(new ServBean(1,"找工作", R.mipmap.job));
        rows.add(new ServBean(2,"城市地铁", R.mipmap.metro));
        rows.add(new ServBean(2,"生活缴费", R.mipmap.pay));
        rows.add(new ServBean(2,"看电影", R.mipmap.movie));
        rows.add(new ServBean(2,"活动管理", R.mipmap.act));
        rows.add(new ServBean(2,"更多", R.mipmap.data));
//        rows.add(new ServBean(3,"停哪儿", R.mipmap.park));
//        rows.add(new ServBean(3,"智慧交管", R.mipmap.car));
        binding.rvGrid.setAdapter(new ServiceGridAdapter(rows, getActivity(), HomeFragment.this));

        //请求轮播图数据
        NetWorkApi.createService(RetroApi.class).getBannerBean().enqueue(new Callback<BannerBean>() {
            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                BannerBean body = response.body();
                if (body.getCode() == 200) {
                    List<BannerBean.RowsBean> rows = body.getRows();
                    initBanner(rows);
                } else {
                    Toast.makeText(getActivity(), body.getCode() + " " + body.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable throwable) {
                Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initBanner(final List<BannerBean.RowsBean> rows) {
        BannerImageAdapter<BannerBean.RowsBean> adapter = new BannerImageAdapter<BannerBean.RowsBean>(rows) {
            @Override
            public void onBindView(BannerImageHolder bannerImageHolder, BannerBean.RowsBean rowsBean, int i, int i1) {
                Glide.with(getActivity())
                        .load(SPUtil.getString(Constant.IP_PORT, "http://124.93.196.45:10001") + rowsBean.getAdvImg())
                        .into(bannerImageHolder.imageView);
            }
        };
        binding.banner.setAdapter(adapter)
                .addBannerLifecycleObserver(getActivity())
                .setIndicator(new CircleIndicator(getActivity()))
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(Object o, int i) {
                        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                        intent.putExtra(Constant.NEWS_ID, rows.get(i).getTargetId());
                        startActivity(intent);
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}