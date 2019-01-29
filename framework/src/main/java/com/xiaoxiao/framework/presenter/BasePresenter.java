package com.xiaoxiao.framework.presenter;

/**
 * Created by caixiaoxiao on 11/8/16.
 */
public class BasePresenter {

    private boolean viewAttached;

    public void onAttachView() {
        this.viewAttached = true;
    }

    public void loadData(){

    }

    public void onDestory(){

    }

    public void onDetachView(){
        this.viewAttached = false;
    }

    public boolean isViewAttached(){
        return this.viewAttached;
    }
}
