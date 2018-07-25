package com.xiaoxiao.base.presenter;

import com.xiaoxiao.base.contract.ISportRoomContract;
import com.xiaoxiao.base.model.SportRoomModelImpl;

/**
 * Author: caixiaoxiao
 * Created on 11/2/18
 */
public class SportRoomPresenterImpl implements ISportRoomContract.Presenter {

    private ISportRoomContract.Model model;
    private ISportRoomContract.View view;

    @Override
    public void init(ISportRoomContract.View view) {
        this.view = view;
        model = new SportRoomModelImpl();
    }

    @Override
    public void unInit() {

    }

}