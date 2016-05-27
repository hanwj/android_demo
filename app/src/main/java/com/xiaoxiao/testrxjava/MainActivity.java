package com.xiaoxiao.testrxjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xiaoxiao.testrxjava.service.ServiceActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caixiaoxiao on 27/5/16.
 */
public class MainActivity extends FragmentActivity {
    private Map<String,Class<?>> funcMap = new HashMap<String,Class<?>>(){
        {
            put("toolbar",ToolbarActivity.class);
            put("anim",TestActivity.class);
            put("service", ServiceActivity.class);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewGroup rootView = (ViewGroup)findViewById(R.id.root_view);
        for (Map.Entry<String,Class<?>> entry:funcMap.entrySet()){
            Button button = new Button(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(30, 30, 30, 30);
            button.setLayoutParams(layoutParams);
            button.setText(entry.getKey());
            rootView.addView(button);

            final Class<?> cls = entry.getValue();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,cls);
                    startActivity(intent);
                }
            });
        }
    }
}
