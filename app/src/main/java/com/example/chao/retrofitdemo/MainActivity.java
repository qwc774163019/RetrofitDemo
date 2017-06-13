package com.example.chao.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.chao.retrofitdemo.service.GitHubClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static String TAG="QWC";
    Button btn;
    Button btn1;
    Button btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn= (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        btn1= (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2= (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                GitHubClient gitHubClient = ServiceGenerator.createService(GitHubClient.class);
                Call<List<GitHubClient.Contributor>> call = gitHubClient.contributors("apis-is", "apis");
//                try {
//                    List<GitHubClient.Contributor> contributors = call.execute().body();
//                    for (GitHubClient.Contributor contributor : contributors) {
//                        Log.i("QWC",contributor.login + " (" + contributor.contributions + ")");
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                call.enqueue(new Callback<List<GitHubClient.Contributor>>() {
                    @Override
                    public void onResponse(Call<List<GitHubClient.Contributor>> call, Response<List<GitHubClient.Contributor>> response) {
                        List<GitHubClient.Contributor> contributors = response.body();
                        if(contributors!=null){
                            for (GitHubClient.Contributor contributor : contributors) {
//                                Log.i("QWC",contributor.login + " (" + contributor.contributions + ")");
                            }
                        }else{
                            Log.i(TAG,"onResponse-onFailure");
                        }

                    }

                    @Override
                    public void onFailure(Call<List<GitHubClient.Contributor>> call, Throwable t) {
                        Log.i(TAG,"onFailure");
                    }
                });

                break;
            case R.id.btn1:
                ServiceGenerator.createService(GitHubClient.class).contributorsNew("apis-is", "apis")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<GitHubClient.Contributor>>() {
                            @Override
                            public void onCompleted() {
                                Log.i(TAG,"onComplete");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i(TAG,"onError");
                            }

                            @Override
                            public void onNext(List<GitHubClient.Contributor> contributors) {
                                for (int i=0;i<contributors.size();i++){
                                    Log.i(TAG,contributors.get(i).getLogin()+":"+contributors.get(i).getContributions()+"\n");
                                }
                            }
                        });

                break;
            case R.id.btn2:

                break;
        }
    }
}
