package com.xiaoxiao.testrxjava.testkeyboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoxiao.testrxjava.R;

/**
 * Created by caixiaoxiao on 11/7/16.
 */
public class InputFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_input,null);
//        return super.onCreateView(inflater, container, savedInstanceState);
        return v;
    }
}
