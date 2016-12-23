package com.xiaoxiao.testrxjava;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

/**
 * Created by caixiaoxiao on 23/12/16.
 */
public class ActionBarActivity extends FragmentActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        SpinnerAdapter mSpinnerAdapter = new ArrayAdapter<CharSequence>(
                this,android.R.layout.simple_spinner_dropdown_item,new String[]{"支付","红包","朋友"});
        ActionBar.OnNavigationListener mOnNavigationListener = new ActionBar.OnNavigationListener() {
            @Override
            public boolean onNavigationItemSelected(int itemPosition, long itemId) {
                return true;
            }
        };
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actionBar.setListNavigationCallbacks(mSpinnerAdapter, mOnNavigationListener);
        setContentView(R.layout.activity_action_bar);
    }
}
