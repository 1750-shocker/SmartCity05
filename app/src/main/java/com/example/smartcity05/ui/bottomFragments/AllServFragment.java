package com.example.smartcity05.ui.bottomFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.smartcity05.R;
import com.example.smartcity05.bean.LevelBean;
import com.example.smartcity05.bean.ServBean;
import com.example.smartcity05.databinding.FragmentAllservBinding;
import com.example.smartcity05.ui.adapter.LevelAdapter;

import java.util.ArrayList;
import java.util.List;

public class AllServFragment extends Fragment {

    private FragmentAllservBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAllservBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.recyclerView.setAdapter(new LevelAdapter(getLevelOneList()));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private ArrayList<LevelBean> getLevelOneList() {
        ArrayList<LevelBean> list = new ArrayList<>();

        List<ServBean> rows1 = new ArrayList<>();
        rows1.add(new ServBean(1, "智慧巴士", R.mipmap.bus));
        rows1.add(new ServBean(1, "门诊预约", R.mipmap.hospital));
        rows1.add(new ServBean(1, "外卖订餐", R.mipmap.waimai));
        rows1.add(new ServBean(1, "找房子", R.mipmap.house));
        rows1.add(new ServBean(1, "找工作", R.mipmap.job));
        list.add(new LevelBean("便民生活", rows1));

        List<ServBean> rows2 = new ArrayList<>();
        rows2.add(new ServBean(2, "城市地铁", R.mipmap.metro));
        rows2.add(new ServBean(2, "生活缴费", R.mipmap.pay));
        rows2.add(new ServBean(2, "看电影", R.mipmap.movie));
        rows2.add(new ServBean(2, "活动管理", R.mipmap.act));
        rows2.add(new ServBean(2, "数据分析", R.mipmap.data));
        list.add(new LevelBean("生活服务", rows2));

        List<ServBean> rows3 = new ArrayList<>();
        rows3.add(new ServBean(3, "停哪儿", R.mipmap.park));
        rows3.add(new ServBean(3, "智慧交管", R.mipmap.car));
        list.add(new LevelBean("车主服务", rows3));

        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}