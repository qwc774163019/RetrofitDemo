package com.example.chao.retrofitdemo.interceptor;

import com.example.chao.retrofitdemo.entity.AccessToken;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author chao
 * @Description {todo}
 * @date 2017/6/8 上午11:18
 * @todo:
 */
public class AuthenticationInterceptor implements Interceptor {
    private AccessToken accessToken;

    public AuthenticationInterceptor(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request build = request.newBuilder().addHeader("Accept", "application/json")
                .addHeader("Authorization", accessToken.getTokenType() + " " + accessToken.getAccessToken())
                .method(request.method(), request.body())
                .build();
        return chain.proceed(build);
    }
}
