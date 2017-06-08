package com.example.chao.retrofitdemo.entity;

import java.io.Serializable;

/**
 * @author chao
 * @Description {todo}
 * @date 2017/6/7 下午6:02
 * @todo:
 */
public class User implements Serializable {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
