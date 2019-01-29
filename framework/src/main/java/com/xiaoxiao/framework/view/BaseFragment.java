package com.xiaoxiao.framework.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoxiao.framework.presenter.BaseFragmentPresenter;

/**
 * Created by caixiaoxiao on 11/8/16.
 */
public abstract class BaseFragment<P extends BaseFragmentPresenter> extends Fragment implements ViewInterface{
    protected P mPresenter;
    private View rootView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initArgs();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(),null);
        initViews();
        addListeners();
        loadData();
        return rootView;
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
//        } catch (java.lang.InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null){
            mPresenter.onDestory();
            mPresenter.onDetachView();
        }
        super.onDestroyView();
    }

    public View getRootView() {
        return rootView;
    }

    public <T extends View> T findViewById(int id){
        return (T) rootView.findViewById(id);
    }

    protected abstract P createPresenter();
}
