package com.xiaoxiao.framework.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaoxiao.framework.R;
import com.xiaoxiao.framework.presenter.BaseFragmentPresenter;

import java.lang.reflect.ParameterizedType;

/**
 * Created by caixiaoxiao on 11/8/16.
 */
public abstract class BaseFragment<P extends BaseFragmentPresenter> extends Fragment implements ViewInterface{
    protected P mPresenter;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(),null);
        initArgs();
        initViews();
        addListeners();
        mPresenter = createPresenter();
        mPresenter.onCreate(this);
        mPresenter.loadData();
        return view;
    }

    private P createPresenter(){
        Class genericClass = (Class)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        try {
            return (P)genericClass.newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T extends View> T findViewById(int id){
        return (T)view.findViewById(id);
    }

    public View getView() {
        return view;
    }

    @Override
    public View getLayout() {
        return null;
    }
}
