package com.example.chao.retrofitdemo.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author chao
 * @Description {todo}
 * @date 2017/6/7 下午5:42
 * @todo:
 */
public class CredientialInterceptor implements Interceptor {
    private String basic;

    public CredientialInterceptor(String basic) {
        this.basic = basic;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder requestBuilder = original.newBuilder()
                .header("Authorization", basic)
                .header("Accept", "application/json")
                .method(original.method(), original.body());

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
