package com.example.smartcity05.ui.activity;


import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.smartcity05.bean.NewsBean;
import com.example.smartcity05.databinding.ActivityNewsDetailBinding;
import com.example.smartcity05.util.BaseActivity;
import com.example.smartcity05.util.Constant;
import com.example.smartcity05.util.MDBHelper;
import com.example.smartcity05.util.SPUtil;

import java.sql.SQLException;
import java.util.List;

public class NewsDetailActivity extends BaseActivity {

    private ActivityNewsDetailBinding binding;
    private int index;
    private NewsBean newsBean = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewsDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        index = getIntent().getIntExtra(Constant.NEWS_ID, 1);
        setToolbar(binding.toolbar, "新闻详情");
        List<NewsBean> rows = null;
        try {
            rows = MDBHelper.getInstance(this).getNewsBeanDao().queryBuilder().where().eq("id", index).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rows.size() != 0) {
            newsBean = rows.get(0);
        }
        //设置封面
        Glide.with(this).load(SPUtil.getString(Constant.IP_PORT, "") + newsBean.getCover()).into(binding.ivDetailCover);
        //设置标题
        binding.tvDetailTitle.setText(binding.tvDetailTitle.getText());
        //设置WebView
        binding.webView.loadDataWithBaseURL(SPUtil.getString(Constant.IP_PORT, ""),
                setWebViewImg(newsBean.getContent()),
                "text/html",
                "utf-8",
                null);
    }

    private String setWebViewImg(String content) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\">" +
                "<style>img{max-width:100%;width:auto;height:auto}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + content + "</body></html>";
    }
}