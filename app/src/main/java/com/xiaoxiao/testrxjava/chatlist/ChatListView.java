package com.xiaoxiao.testrxjava.chatlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaoxiao.testrxjava.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzapp on 18/1/16.
 */

public class ChatListView extends RelativeLayout{
    private RecyclerView recyclerView;
    private TextView moreTipTv;
    private ChatAdapter adapter;
    private List<String> chatList = new ArrayList<>();

    private int unread = 0;

    private ChatListPresenter presenter;
    public ChatListView(@NonNull Context context) {
        this(context,null);
    }

    public ChatListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ChatListView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.view_chat_list,this,true);
        moreTipTv = (TextView) findViewById(R.id.chat_more_tip);
        recyclerView = (RecyclerView) findViewById(R.id.chat_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(null);
        recyclerView.setAdapter(adapter = new ChatAdapter(context));


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (RecyclerView.SCROLL_STATE_IDLE == newState){
                    if (isBottom()){
                        loadMore();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        moreTipTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                unread = 0;
                adapter.addAll(chatList);
                recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
                moreTipTv.setVisibility(View.GONE);
                chatList.clear();
            }
        });

        presenter = new ChatListPresenter(this);
    }

    /**
     * @param msg
     */
    public void addItem(String msg){
        if (isBottom() && chatList.size() == 0){
            unread = 0;
            moreTipTv.setVisibility(View.GONE);
            adapter.add(msg);
            recyclerView.scrollToPosition(adapter.getItemCount() - 1);
        }else {
            chatList.add(msg);
            moreTipTv.setVisibility(View.VISIBLE);
            unread++;
            moreTipTv.setText(unread + "条新消息");
        }
    }

    public void loadMore(){
        if (chatList.size() > 0){
            //每次最多加载5个
            List more = new ArrayList();
            for (int i = 0; i < 5; i++){
                if (chatList.size() > 0){
                    more.add(chatList.remove(0));
                }
            }
            adapter.addAll(more);
        }else {
            unread = 0;
            moreTipTv.setVisibility(View.GONE);
        }
    }

    public boolean isBottom(){
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int totalItemCount = layoutManager.getItemCount();
        int visibleItemCount = layoutManager.getChildCount();
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        if (lastVisibleItemPosition >=  totalItemCount - 1){
            return true;
        }
        return false;
    }
}
