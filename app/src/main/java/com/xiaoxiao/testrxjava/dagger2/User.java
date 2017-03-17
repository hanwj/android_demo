package com.xiaoxiao.testrxjava.dagger2;

import javax.inject.Inject;

/**
 * Created by caixiaoxiao on 1/3/17.
 */
public class User {
    private String name;

    @Inject
    public User(){

    }

    public void setName(String name){
        this.name = name;
    }
}
