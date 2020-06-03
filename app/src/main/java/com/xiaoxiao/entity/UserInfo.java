package com.xiaoxiao.entity;

import java.io.Serializable;

/**
 * 文件名: UserInfo
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2020/5/20
 */
public class UserInfo implements Serializable {
    private String uid;
    private String name;
    private String avatar;

    public UserInfo(String uid,String name,String avatar){
        this.uid = uid;
        this.name = name;
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return super.toString() + "(uid:" + uid + ",name:" + name + ",avatar:" + avatar + ")";
    }
}
