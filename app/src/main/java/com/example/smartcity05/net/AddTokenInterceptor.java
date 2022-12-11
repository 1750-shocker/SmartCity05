package com.example.smartcity05.net;


import com.example.smartcity05.util.Constant;
import com.example.smartcity05.util.SPUtil;
import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddTokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        //这里从SharedPreference中获取登录时得到的token，如果返回401再在toast中提示重新登录
        String token = SPUtil.getString(Constant.TOKEN, "");
        Request request = chain.request().newBuilder().header("Authorization", token).build();
        return chain.proceed(request);
    }
}
