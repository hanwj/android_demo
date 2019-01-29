package com.xiaoxiao.framework.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xiaoxiao.framework.presenter.BaseActivityPresenter;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;

/**
 * Created by caixiaoxiao on 11/8/16.
 */
public abstract class BaseActivity<P extends BaseActivityPresenter>extends AppCompatActivity implements ViewInterface{
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initArgs();
        setContentView(getLayoutId());
        initViews();
        addListeners();
        loadData();
    }

    @Override
    public void loadData() {
        mPresenter = createPresenter();
        if (mPresenter != null){
            mPresenter.onAttachView();
            mPresenter.onCreate(this);
        }
    }

//    private P createPresenter(){
//        Class genericClass = (Class)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//        try {
//            return (P)genericClass.newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null){
            mPresenter.onDestory();
            mPresenter.onDetachView();
        }
        super.onDestroy();
    }

    protected abstract P createPresenter();
}
