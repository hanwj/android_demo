package com.xiaoxiao.framework.presenter;

import com.xiaoxiao.framework.view.BaseDialogFragment;

/**
 * Created by caixiaoxiao on 11/8/16.
 */
public class BaseDialogFragPresenter<D extends BaseDialogFragment> extends BasePresenter{
    protected D mDialogFrag;
    public void onCreate(D dialogFrag){
        this.mDialogFrag = dialogFrag;
        loadData();
    }

    @Override
    public void onDetachView() {
        super.onDetachView();
        this.mDialogFrag = null;
    }
}
