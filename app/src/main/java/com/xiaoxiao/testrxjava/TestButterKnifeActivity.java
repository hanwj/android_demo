package com.xiaoxiao.testrxjava;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by caixiaoxiao on 7/2/17.
 */
public class TestButterKnifeActivity extends FragmentActivity{
    @BindView(R.id.butter_knife_tv)TextView tv;
    @BindView(R.id.butter_knife_iv)ImageView iv;
    @BindView(R.id.butter_knife_btn)Button btn;
    @BindString(R.string.click)String clickStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_butter_knife);
        ButterKnife.bind(this);

//        tv.setText("bufferKnife文本");
        tv.setText(clickStr);
        iv.setImageResource(R.mipmap.ic_menu_search);
        iv.setBackgroundColor(Color.BLACK);
    }

    @OnClick(R.id.butter_knife_btn)
    public void onClickBtn(View v){
        Toast.makeText(this,clickStr,Toast.LENGTH_SHORT).show();
    }
}
