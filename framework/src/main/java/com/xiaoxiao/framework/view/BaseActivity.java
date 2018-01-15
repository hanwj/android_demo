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
        if (getLayoutId() > 0){
            setContentView(getLayoutId());
        }else {
            setContentView(getLayout());
        }
        initArgs();
        initViews();
        addListeners();
        mPresenter = createPresenter();
        mPresenter.onCreate(this);
        Collections.sort();
    }

    private P createPresenter(){
        Class genericClass = (Class)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        try {
            return (P)genericClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public View getLayout() {
        return null;
    }

    /**声明周期start**/
    @Override
    protected void onResume() {
        mPresenter.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mPresenter.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestory();
        super.onDestroy();
    }
    /**声明周期end**/
}
