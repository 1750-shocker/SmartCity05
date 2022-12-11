package com.example.smartcity05.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity05.bean.OrderListBean;
import com.example.smartcity05.databinding.ItemOrderBinding;
import com.example.smartcity05.util.BaseActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ItemHolder> {

    private List<OrderListBean.RowsBean> beans = new ArrayList<>();
    private OrderListBean.RowsBean rowsBean;
    private Context context;

    public OrderListAdapter(Context context,List<OrderListBean.RowsBean> beans) {
        this.context = context;
        this.beans = beans;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(ItemOrderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        rowsBean = beans.get(position);
        holder.binding.tvOrderDate.setText(rowsBean.getCreateTime());
        holder.binding.tvOrderId.setText(rowsBean.getOrderNo());
        holder.binding.tvOrderState.setText(rowsBean.getOrderStatus());
        holder.binding.tvOrderType.setText(rowsBean.getOrderType());
        holder.binding.llOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:订单详情
                Toast.makeText(context, "敬请期待", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }


    public class ItemHolder extends RecyclerView.ViewHolder {
        private ItemOrderBinding binding;

        public ItemHolder(ItemOrderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
