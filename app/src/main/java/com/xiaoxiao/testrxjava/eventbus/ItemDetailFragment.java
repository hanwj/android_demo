package com.xiaoxiao.testrxjava.eventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaoxiao.testrxjava.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by meibo-design on 18/5/16.
 */
public class ItemDetailFragment extends Fragment {
    private TextView txt;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_item_detail,null);
        txt = (TextView)v.findViewById(R.id.item_text);
        return v;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ItemListFragment.Item item){
        txt.setText(item.content);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
