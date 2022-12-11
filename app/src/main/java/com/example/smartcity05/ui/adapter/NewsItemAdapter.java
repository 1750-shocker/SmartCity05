package com.example.smartcity05.ui.adapter;

import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity05.bean.NewsBean;
import com.example.smartcity05.databinding.ItemNewslistBinding;
import com.example.smartcity05.ui.activity.NewsDetailActivity;
import com.example.smartcity05.util.Constant;
import com.example.smartcity05.util.SPUtil;

import java.util.List;

public class NewsItemAdapter extends RecyclerView.Adapter<NewsItemAdapter.ItemHolder> {

    private List<NewsBean> rows;

    public NewsItemAdapter(List<NewsBean> rows) {
        this.rows = rows;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(ItemNewslistBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, int position) {
        final NewsBean row = rows.get(position);
        holder.binding.tvItemBrowse.setText(row.getReadNum() + "");
        holder.binding.tvItemContent.setText(Html.fromHtml(row.getContent()));
        holder.binding.tvItemDate.setText(row.getPublishDate());
        holder.binding.tvItemLike.setText(row.getLikeNum() + "");
        holder.binding.tvItemTitle.setText(row.getTitle());
        Glide.with(holder.itemView.getContext()).load(SPUtil.getString(Constant.IP_PORT, "http://124.93.196.45:10001") + row.getCover()).into(holder.binding.ivNewsItemCover);
        holder.binding.itemLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), NewsDetailActivity.class);
                intent.putExtra(Constant.NEWS_ID, row.getId());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        private ItemNewslistBinding binding;
        public ItemHolder(ItemNewslistBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
