package com.xiaoxiao.framework.presenter;

import com.xiaoxiao.framework.view.BaseFragment;

/**
 * Created by caixiaoxiao on 11/8/16.
 */
public class BaseFragmentPresenter<F extends BaseFragment> extends BasePresenter{
    protected F mFragment;
    public void onCreate(F frag){
        this.mFragment = frag;
        loadData();
    }
}
