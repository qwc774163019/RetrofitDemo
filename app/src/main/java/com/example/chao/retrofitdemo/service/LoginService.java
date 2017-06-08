package com.example.chao.retrofitdemo.service;

import com.example.chao.retrofitdemo.entity.AccessToken;
import com.example.chao.retrofitdemo.entity.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author chao
 * @Description {todo}
 * @date 2017/6/7 下午5:47
 * @todo:
 */
public interface LoginService {

    @POST("/login")
    Call<User> basicLogin();

    @FormUrlEncoded
    @POST("/token")
    Call<AccessToken> getAccessToken(@Field("code") String code,@Field("grant_type") String grantType);
}
