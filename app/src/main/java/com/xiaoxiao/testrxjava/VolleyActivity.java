package com.xiaoxiao.testrxjava;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Layout;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


/**
 * Created by caixiaoxiao on 11/8/16.
 */
public class VolleyActivity extends FragmentActivity{
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout view = new FrameLayout(this);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(-1,-1);
        view.setLayoutParams(lp);
        setContentView(view);
        text = new TextView(this);
        lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        text.setLayoutParams(lp);
        text.setTextColor(Color.BLACK);
        view.addView(text);
        testVolley();
    }

    private void testVolley(){
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = "http://dev1.xcyo.com/app/system/start";
//        url = "http://192.168.1.131/test.php";
//        final StringBuilder builder = new StringBuilder(new String("请求:" + url + "\n"));
//        text.setText(builder);
////        queue.add(new StringRequest(url,
////                new Response.Listener<String>() {
////                    @Override
////                    public void onResponse(String response) {
////                        Log.e("test", response);
////                        builder.append("请求结果:" + response);
////                        text.setText(builder);
////                    }
////                }, new Response.ErrorListener() {
////            @Override
////            public void onErrorResponse(VolleyError error) {
////                builder.append("请求出错:" + error);
////                text.setText(builder);
////            }
////        }));
//
////        url = "http://dev1.xcyo.com/app/passport/login";
//        url = "http://192.168.1.131/test.php";
//        StringRequest request = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                builder.append("请求结果:" + response);
//                text.setText(builder);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                builder.append("请求出错:" + error);
//                text.setText(builder);
//            }
//        });
//        queue.add(request);
    }
}
