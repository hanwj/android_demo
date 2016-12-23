package com.xiaoxiao.testrxjava.actionBar;

import android.content.Context;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;

import com.xiaoxiao.testrxjava.R;

/**
 * Created by caixiaoxiao on 23/12/16.
 */
public class MyActionProvider extends ActionProvider{
    private Context mCtx;
    /**
     * Creates a new instance. ActionProvider classes should always implement a
     * constructor that takes a single Context parameter for inflating from menu XML.
     *
     * @param context Context for accessing resources.
     */
    public MyActionProvider(Context context) {
        super(context);
        this.mCtx = context;
    }

    @Override
    public View onCreateActionView() {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.layout_my_action_provider,null);
        return view;
    }
}
