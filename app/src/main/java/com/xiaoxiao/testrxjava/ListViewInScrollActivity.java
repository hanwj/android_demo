package com.xiaoxiao.testrxjava;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;

import com.xiaoxiao.view.MyScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caixiaoxiao on 14/7/16.
 */
public class ListViewInScrollActivity extends FragmentActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_in_scroll);
        ScrollView  mScroll = (ScrollView) findViewById(R.id.scroll);
        ListView mList = (ListView)findViewById(R.id.listview);
        ((MyScrollView)mScroll).setList(mList);


        mList.setAdapter(new ListAdapter(this, new ArrayList<String>()));
    }

    class ListAdapter extends BaseAdapter{

        private Context mCtx;
        private List<String> mListData;
        public ListAdapter(Context ctx,List<String> listData){
            mCtx = ctx;
            mListData = listData;
        }
        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int position) {
            return 20;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = LayoutInflater.from(mCtx).inflate(R.layout.item_list,null);
            return v;
        }
    }
}
