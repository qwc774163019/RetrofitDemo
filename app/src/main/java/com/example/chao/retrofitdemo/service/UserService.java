package com.example.chao.retrofitdemo.service;

import com.example.chao.retrofitdemo.entity.User;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * @author chao
 * @Description {todo}
 * @date 2017/6/7 下午6:01
 * @todo:
 */
public interface UserService {
    @POST("/me")
    Call<User> me();

}
