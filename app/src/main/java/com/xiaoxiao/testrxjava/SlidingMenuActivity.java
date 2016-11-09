package com.xiaoxiao.testrxjava;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.xiaoxiao.view.SlidingMenu;

/**
 * Created by caixiaoxiao on 9/11/16.
 */
public class SlidingMenuActivity extends FragmentActivity{

    private SlidingMenu mSlidingMenu;
    private Button mOpenBtn,mCloseBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_menu);
        mSlidingMenu = (SlidingMenu) findViewById(R.id.sliding_menu);
        mOpenBtn = (Button) findViewById(R.id.sliding_menu_open_menu);
        mCloseBtn = (Button) findViewById(R.id.sliding_menu_close_menu);

        mOpenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlidingMenu.openMenu();
            }
        });

        mCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlidingMenu.closeMenu();
            }
        });
    }
}
