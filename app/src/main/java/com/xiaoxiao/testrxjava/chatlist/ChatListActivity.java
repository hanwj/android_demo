package com.xiaoxiao.testrxjava.chatlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xiaoxiao.testrxjava.R;

/**
 * Created by lzapp on 18/1/16.
 */

public class ChatListActivity extends FragmentActivity{
    View addBtn;
    ChatListView listView;
    private int count = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        addBtn = findViewById(R.id.chat_add);
        listView = (ChatListView) findViewById(R.id.chat_list);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.addItem("消息" + (++count));
            }
        });
    }
}
