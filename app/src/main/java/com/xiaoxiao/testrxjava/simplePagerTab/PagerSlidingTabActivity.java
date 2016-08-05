package com.xiaoxiao.testrxjava.simplePagerTab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.xiaoxiao.testrxjava.R;
import com.xiaoxiao.view.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caixiaoxiao on 4/8/16.
 */
public class PagerSlidingTabActivity extends FragmentActivity{
    private ViewPager mPager;
    private PagerSlidingTabStrip mPagerTab;
    private List<Fragment> mFrags;
    private String[] mPagerTitles = {"测试1 正 常","测试2短","测试3长长长长长","测4"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_sliding_tab);
        mPager = (ViewPager)findViewById(R.id.act_pager);
        mPagerTab = (PagerSlidingTabStrip)findViewById(R.id.act_pager_tab);

        int count = mPagerTitles.length;
        mFrags = new ArrayList<>();
        for (int i = 0;i < count;i++){
            mFrags.add(new SimpleFragment().setIndex(i));
        }

        mPager.setOffscreenPageLimit(mFrags.size());
        mPager.setAdapter(new SimpleViewPagerAdapter(getSupportFragmentManager()));
        mPagerTab.setViewPager(mPager);
    }

    class SimpleViewPagerAdapter extends FragmentPagerAdapter{

        public SimpleViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFrags.get(position);
        }

        @Override
        public int getCount() {
            return mFrags.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            return ((SimpleFragment)mFrags.get(position)).getTitle();
            return mPagerTitles[position];
        }
    }

}
