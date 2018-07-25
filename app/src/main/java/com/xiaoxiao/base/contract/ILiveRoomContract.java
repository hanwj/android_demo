package com.xiaoxiao.base.contract;

import com.xiaoxiao.base.BasePresenter;
import com.xiaoxiao.base.BaseView;

/**
 * Author: caixiaoxiao
 * Created on 11/2/18
 */
public interface ILiveRoomContract {

    interface View extends BaseView {
        //TODO add abstract method related with view
    }

    interface Presenter extends BasePresenter<View> {
        //TODO add abstract method for Presenter

    }

    interface Model {
        //TODO add abstract method for Model
    }
}