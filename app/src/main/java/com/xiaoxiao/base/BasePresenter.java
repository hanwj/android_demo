package com.xiaoxiao.base;

/**
 * Author: caixiaoxiao
 * Created on 11/2/18
 */
public interface BasePresenter<V extends BaseView> {
    void init(V baseView);

    void unInit();
}