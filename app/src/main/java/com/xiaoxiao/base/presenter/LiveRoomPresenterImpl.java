package com.xiaoxiao.base.presenter;

import com.xiaoxiao.base.contract.ILiveRoomContract;
import com.xiaoxiao.base.model.LiveRoomModelImpl;

/**
 * Author: caixiaoxiao
 * Created on 11/2/18
 */
public class LiveRoomPresenterImpl implements ILiveRoomContract.Presenter {

    private ILiveRoomContract.Model model;
    private ILiveRoomContract.View view;

    @Override
    public void init(ILiveRoomContract.View view) {
        this.view = view;
        model = new LiveRoomModelImpl();
    }

    @Override
    public void unInit() {

    }

}