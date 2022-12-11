package com.example.smartcity05.net;


import com.example.smartcity05.util.Constant;
import com.example.smartcity05.util.SPUtil;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWorkApi {
    private static OkHttpClient okHttpClient;

    private static String getBaseUrl() {
        //赛队训练使用（http://124.93.196.45:10001)。
        return SPUtil.getString(Constant.IP_PORT, "http://124.93.196.45:10001");
    }

    public static <T> T createService(Class<T> serviceClass) {
        return getRetrofit().create(serviceClass);
    }

    public static <T> T createServiceWithoutClient(Class<T> serviceClass) {
        return getRetrofitWithoutClient().create(serviceClass);
    }

    public static RequestBody getRequestBody(JSONObject jsonObject) {
        return RequestBody.create(MediaType.parse("application/json;charset=utf-8"),String.valueOf(jsonObject));
    }

    private static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new AddTokenInterceptor());
            okHttpClient = builder.build();
        }
        return okHttpClient;
    }

    private static Retrofit getRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(getBaseUrl());
        builder.client(getOkHttpClient());
        builder.addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }

    private static Retrofit getRetrofitWithoutClient() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(getBaseUrl());
//        builder.client(getOkHttpClient());
        builder.addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }

}
