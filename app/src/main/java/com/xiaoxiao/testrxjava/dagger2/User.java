package com.xiaoxiao.testrxjava.dagger2;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by caixiaoxiao on 1/3/17.
 */
public class User {
    private String name;

    public User(){
        name = "123";
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
