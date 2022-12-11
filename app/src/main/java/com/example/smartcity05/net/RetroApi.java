package com.example.smartcity05.net;


import com.example.smartcity05.bean.BannerBean;
import com.example.smartcity05.bean.LoginBean;
import com.example.smartcity05.bean.MsgBean;
import com.example.smartcity05.bean.NewsListBean;
import com.example.smartcity05.bean.OrderListBean;
import com.example.smartcity05.bean.RegisterBean;
import com.example.smartcity05.bean.UserInfoBean;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface RetroApi {
    //用户登录
    @POST("/prod-api/api/login")
    Call<LoginBean> getLoginBean(@Body RequestBody requestBody);

    //用户注册
    @POST("/prod-api/api/register")
    Call<RegisterBean> getRegisterBean(@Body RequestBody requestBody);

    //首页新闻轮播图
    @GET("/prod-api/api/rotation/list?type=2")
    Call<BannerBean> getBannerBean();

    @GET("/prod-api/press/press/list")
    Call<NewsListBean> getNewsListBean();

    @POST("/prod-api/api/common/feedback")
    Call<MsgBean> getFeedBackBean(@Body RequestBody body);

    @PUT("/prod-api/api/common/user/resetPwd")
    Call<MsgBean> getModifyPswBean(@Body RequestBody body);

    @GET("/prod-api/api/allorder/list")
    Call<OrderListBean> getOrderListBean();

    @GET("/prod-api/api/common/user/getInfo")
    Call<UserInfoBean> getUserInfoBean();

    @PUT("/prod-api/api/common/user")
    Call<MsgBean> getChangeUserInfoBean(@Body RequestBody body);

}
