package com.xiaoxiao.testrxjava.chatlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaoxiao.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzapp on 18/1/16.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder>{
    private final static int MAX_NUM = 30;

    private Context mContext;
    private List<String> listData;

    public ChatAdapter(Context context){
        mContext = context;
        listData = new ArrayList<>();
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = new TextView(mContext);
        textView.setTextSize(14);
        ChatViewHolder holder = new ChatViewHolder(textView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        holder.tv.setText(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void add(String item){
        listData.add(item);
        notifyItemInserted(getItemCount() - 1);
        if (listData.size() > MAX_NUM){
            listData.remove(0);
            notifyItemRemoved(0);
        }
    }

    public void addAll(List<String> list){
        if (list == null || list.size() == 0){
            return;
        }
        int old = listData.size();
        int insert = list.size();
        int total = old + insert;
        listData.addAll(list);
        if (total > MAX_NUM){
            LogUtils.e("ChatAdapter","total:" + total + ",old:" + old + ",insert:" + insert);
            int more = total - MAX_NUM;
            listData = new ArrayList<>(listData.subList(more,total));
            if (insert > MAX_NUM){
                notifyDataSetChanged();
            }else {
                notifyItemRangeRemoved(0,more);
                notifyItemRangeInserted(old - more,insert);
            }
        }else {
            notifyItemRangeInserted(old,insert);
        }
    }

    class ChatViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public ChatViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView;
        }
    }
}
