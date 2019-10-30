package com.xiaoxiao.testrxjava;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xiaoxiao.utils.Util;

/**
 * 文件名: RecyclerViewActivity
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2019-10-30
 */
public class RecyclerViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerView = findViewById(R.id.rcy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(this));
    }

    static class MyAdapter extends RecyclerView.Adapter<ViewHolder>{
        private Context mContext;

        public MyAdapter(Context context){
            this.mContext = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_rcy,null,false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (holder != null){
                holder.fillData(position);
            }
        }

        @Override
        public int getItemCount() {
            return 12;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView labelView;
        public ViewHolder(View itemView) {
            super(itemView);
            labelView = itemView.findViewById(R.id.item_label);
        }

        public void fillData(int position){
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) labelView.getLayoutParams();
            int offset = 70 + (position%5)*40;
            lp.leftMargin = Util.dp2px(offset);
            labelView.setLayoutParams(lp);

            labelView.setText(position + "");
        }
    }
}
