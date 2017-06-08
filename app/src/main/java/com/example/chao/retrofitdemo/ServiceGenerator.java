package com.example.chao.retrofitdemo;

import android.text.TextUtils;
import android.util.Base64;

import com.example.chao.retrofitdemo.interceptor.AuthTokenInterceptor;
import com.example.chao.retrofitdemo.interceptor.CredientialInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author chao
 * @Description {todo}
 * @date 2017/6/7 下午4:38
 * @todo:
 */
public class ServiceGenerator {
    public static final String API_BASE_URL = "https://api.github.com/";
    private static OkHttpClient.Builder httpClient=new OkHttpClient.Builder();
    private static Retrofit.Builder builder=new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClasss){
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClasss);
    }

    public static <S> S createService(Class<S> serviceClass,String userName,String passWord){
        if(!TextUtils.isEmpty(userName)&&!TextUtils.isEmpty(passWord)){
            String credentials=userName+":"+passWord;
            final String basic="Basic"+ Base64.encodeToString(credentials.getBytes(),Base64.NO_WRAP);
            httpClient.addInterceptor(new CredientialInterceptor(basic));
            OkHttpClient client=httpClient.build();
            Retrofit retrofit=builder.client(client).build();
            return retrofit.create(serviceClass);
        }
        return createService(serviceClass);
    }

    public static <S> S createService(Class<S> serviceClass,String authToken){
        if(!TextUtils.isEmpty(authToken)){
            httpClient.addInterceptor(new AuthTokenInterceptor(authToken));
            OkHttpClient client=httpClient.build();
            Retrofit retrofit=builder.client(client).build();
            return retrofit.create(serviceClass);
        }
        return createService(serviceClass);
    }

}
