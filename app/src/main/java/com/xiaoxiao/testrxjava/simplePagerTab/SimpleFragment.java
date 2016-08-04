package com.xiaoxiao.testrxjava.simplePagerTab;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by caixiaoxiao on 4/8/16.
 */
public class SimpleFragment extends Fragment{
    private int index = -1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getContext());
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        textView.setLayoutParams(lp);
        textView.setText("pager:" + index);
        textView.setTextColor(Color.BLUE);
        return textView;
    }

    public Fragment setIndex(int index){
        this.index = index;
        return this;
    }

    public String getTitle(){
        return "pager" + index;
    }
}
