package com.example.smartcity05.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.smartcity05.R;
import com.example.smartcity05.bean.NewsBean;
import com.example.smartcity05.databinding.FragmentNewsvpBinding;
import com.example.smartcity05.ui.activity.NewsDetailActivity;
import com.example.smartcity05.ui.adapter.NewsItemAdapter;
import com.example.smartcity05.util.Constant;
import com.example.smartcity05.util.MDBHelper;
import com.example.smartcity05.util.SPUtil;
import com.j256.ormlite.stmt.Where;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsVpFragment extends Fragment {
    private int index;
    private List<NewsBean> rows = new ArrayList<>();
    private FragmentNewsvpBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewsvpBinding.inflate(inflater, container, false);
        index = getArguments().getInt(Constant.NEWS_TYPE);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //从数据库读取数据列
        Where<NewsBean, Integer> where = MDBHelper.getInstance(getContext()).getNewsBeanDao().queryBuilder().where();
        try {
            switch (index) {
                case 0:
                    rows = where.eq("type", "9").query();
                    break;
                case 1:
                    rows = where.eq("type", "17").query();
                    break;
                case 2:
                    rows = where.eq("type", "19").query();
                    break;
                case 3:
                    rows = where.eq("type", "20").query();
                    break;
                case 4:
                    rows = where.eq("type", "21").query();
                    break;
                case 5:
                    rows = where.eq("type", "22").query();
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rows.size() != 0) {
            showList();
        } else {
            Toast.makeText(getContext(), "请检查网络连接后重新进入应用", Toast.LENGTH_SHORT).show();
        }
    }

    private void showList() {
        binding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recycler.setAdapter(new NewsItemAdapter(rows));
        initBanner();
    }

    private void initBanner() {
        final List<NewsBean> beans = new ArrayList<>();
        beans.add(rows.get(0));
        beans.add(rows.get(1));
        beans.add(rows.get(2));
        BannerImageAdapter<NewsBean> adapter = new BannerImageAdapter<NewsBean>(beans) {
            @Override
            public void onBindView(BannerImageHolder bannerImageHolder, NewsBean newsBean, int i, int i1) {
                Glide.with(getActivity()).load(SPUtil.getString(Constant.IP_PORT, "http://124.93.196.45:10001") + newsBean.getCover())
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                        .into(bannerImageHolder.imageView);
            }
        };
        binding.banner.setAdapter(adapter)
                .addBannerLifecycleObserver(this)
                .setIndicator(new CircleIndicator(getActivity()))
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(Object o, int i) {
                        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                        intent.putExtra(Constant.NEWS_ID, beans.get(i).getId());
                        startActivity(intent);
                    }
                });
    }

    public static NewsVpFragment newInstance(int i) {
        NewsVpFragment fragment = new NewsVpFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.NEWS_TYPE, i);
        fragment.setArguments(bundle);
        return fragment;
    }
}
