package com.xiaoxiao.framework.presenter;

import com.xiaoxiao.framework.view.BaseActivity;

/**
 * Created by caixiaoxiao on 11/8/16.
 */
public class BaseActivityPresenter<A extends BaseActivity> extends BasePresenter{
    protected A mActivity;
    public void onCreate(A activity){
        this.mActivity = activity;
        loadData();
    }

    @Override
    public void onDetachView() {
        super.onDetachView();
        this.mActivity = null;
    }
}
