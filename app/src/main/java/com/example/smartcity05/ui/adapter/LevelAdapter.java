package com.example.smartcity05.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity05.R;
import com.example.smartcity05.bean.LevelBean;
import com.example.smartcity05.databinding.ItemLevelBinding;

import java.util.List;

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.ItemHolder> {
    List<LevelBean> rows;

    public LevelAdapter(List<LevelBean> rows) {
        this.rows = rows;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHolder itemHolder = new ItemHolder(ItemLevelBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        itemHolder.binding.group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isShow = itemHolder.binding.rvContacts.getVisibility() == View.VISIBLE;
                itemHolder.binding.ivFlag.setImageDrawable(isShow ? ContextCompat.getDrawable(parent.getContext(), R.drawable.ic_baseline_arrow_right_24) : ContextCompat.getDrawable(parent.getContext(), R.drawable.ic_baseline_arrow_drop_down_24));
                itemHolder.binding.rvContacts.setVisibility(isShow ? View.GONE : View.VISIBLE);
            }
        });
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        LevelBean levelBean = rows.get(position);
        holder.binding.tvGroupName.setText(levelBean.getServType());
        holder.binding.rvContacts.setAdapter(new ServiceGridAdapter(levelBean.getServBeanList(), holder.itemView.getContext()));
        holder.binding.rvContacts.setLayoutManager(new GridLayoutManager(holder.itemView.getContext(), 5));
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        private ItemLevelBinding binding;

        public ItemHolder(ItemLevelBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
