package com.example.chao.retrofitdemo.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author chao
 * @Description {todo}
 * @date 2017/6/7 下午5:56
 * @todo:
 */
public class AuthTokenInterceptor implements Interceptor {
    private String authToken;

    public AuthTokenInterceptor(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder=request.newBuilder().header("Authorization",authToken)
                .method(request.method(),request.body());
        Request build = builder.build();
        return chain.proceed(build);
    }
}
