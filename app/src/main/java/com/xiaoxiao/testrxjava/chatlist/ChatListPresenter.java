package com.xiaoxiao.testrxjava.chatlist;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzapp on 18/1/16.
 */

public class ChatListPresenter{
    private RecyclerView mListView;
    private List<String> chatList = new ArrayList<>();
    private ChatListView mView;

    public ChatListPresenter(ChatListView chatListView){
        mView = chatListView;
    }

    public void add(String item){
        if (!mView.isBottom()){
            chatList.add(item);
        }else {

        }
    }

}
