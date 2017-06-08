package com.example.chao.retrofitdemo.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author chao
 * @Description {todo}
 * @date 2017/6/7 下午4:47
 * @todo:
 */
public interface GitHubClient {
    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo
    );

    static class Contributor {
        String login;
        int contributions;
    }
}
