package com.example.chao.retrofitdemo.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

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

    @GET("/repos/{owner}/{repo}/contributors")
    Observable<List<Contributor>> contributorsNew(@Path("owner") String owner, @Path("repo") String repo);

    static class Contributor {
        String login;
        int contributions;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public int getContributions() {
            return contributions;
        }

        public void setContributions(int contributions) {
            this.contributions = contributions;
        }
    }
}
