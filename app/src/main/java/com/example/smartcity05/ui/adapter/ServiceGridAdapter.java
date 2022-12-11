package com.example.smartcity05.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity05.R;
import com.example.smartcity05.bean.ServBean;
import com.example.smartcity05.databinding.ItemGridBinding;
import com.example.smartcity05.ui.bottomFragments.HomeFragment;
import com.example.smartcity05.ui.servActivities.ServAct;
import com.example.smartcity05.util.Constant;

import java.util.List;

public class ServiceGridAdapter extends RecyclerView.Adapter<ServiceGridAdapter.ItemHolder> {
    private List<ServBean> rows;
    private Context context;
    private Fragment fragment;

    public ServiceGridAdapter(List<ServBean> rows, Context context) {
        this.rows = rows;
        this.context = context;
    }

    public ServiceGridAdapter(List<ServBean> rows, Context context, Fragment fragment) {
        this.rows = rows;
        this.context = context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGridBinding binding = ItemGridBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ItemHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int position) {
        itemHolder.binding.ivGrid.setImageResource(rows.get(position).getServImg());
        itemHolder.binding.tvGrid.setText(rows.get(position).getServName());
        itemHolder.binding.llGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = rows.get(position).getServName();
                if (name.equals("智慧巴士")) {
                    context.startActivity(new Intent(context, ServAct.class));
                } else if (name.equals("门诊预约")) {
                    context.startActivity(new Intent(context, ServAct.class));
                }
                if (name.equals("外卖订餐")) {
                    context.startActivity(new Intent(context, ServAct.class));
                }
                if (name.equals("找房子")) {
                    context.startActivity(new Intent(context, ServAct.class));
                }
                if (name.equals("找工作")) {
                    context.startActivity(new Intent(context, ServAct.class));
                }
                if (name.equals("城市地铁")) {
                    context.startActivity(new Intent(context, ServAct.class));
                }
                if (name.equals("生活缴费")) {
                    context.startActivity(new Intent(context, ServAct.class));
                }
                if (name.equals("看电影")) {
                    context.startActivity(new Intent(context, ServAct.class));
                }
                if (name.equals("活动管理")) {
                    context.startActivity(new Intent(context, ServAct.class));
                }
                if (name.equals("更多")) {
//                    Intent intent = new Intent(Constant.ACTION);
//                    context.sendBroadcast(intent);
                    //T用Context去做Navigation跳转
                    NavHostFragment.findNavController(fragment)
                            .navigate(R.id.action_navigation_home_to_navigation_dashboard);
                }
                if (name.equals("停哪儿")) {
                    context.startActivity(new Intent(context, ServAct.class));
                }
                if (name.equals("智慧交管")) {
                    context.startActivity(new Intent(context, ServAct.class));
                }
                if (name.equals("数据分析")) {
                    context.startActivity(new Intent(context, ServAct.class));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        private ItemGridBinding binding;
        public ItemHolder(ItemGridBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
