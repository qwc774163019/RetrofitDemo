package com.example.chao.retrofitdemo.entity;

/**
 * @author chao
 * @Description {todo}
 * @date 2017/6/8 上午11:01
 * @todo:
 */
public class AccessToken {
    private String accessToken;
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        // OAuth requires uppercase Authorization HTTP header value for token type
        if (!Character.isUpperCase(tokenType.charAt(0))) {
            tokenType =Character.toString(tokenType.charAt(0)).toUpperCase() + tokenType.substring(1);
        }

        return tokenType;
    }
}
