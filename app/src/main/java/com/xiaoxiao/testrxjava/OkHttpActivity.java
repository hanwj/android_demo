package com.xiaoxiao.testrxjava;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.xiaoxiao.eventbus.CustomEvent;
import com.xiaoxiao.eventbus.IEventBusReceiver;
import com.xiaoxiao.retrofit.RetrofitServer;
import com.xiaoxiao.server.HomeServerApi;
import com.xiaoxiao.utils.LogUtils;
import com.xiaoxiao.utils.Util;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by caixiaoxiao on 13/2/17.
 */
public class OkHttpActivity extends FragmentActivity implements IEventBusReceiver{
    Handler mHandler = new Handler();
    Button testBtn;
    TextView testView;

    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_okhttp);
        testBtn = (Button) findViewById(R.id.okhttp_test);
        testView = (TextView) findViewById(R.id.okhttp_result);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                final String result = testOkHttp();
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        testView.setText(result);
//                    }
//                });
//            }
//        }).start();
//        testOkHttp();
//        testView.setText(testOkHttp());

        HorizontalScrollView scrollView = new HorizontalScrollView(this);
        radioGroup = new RadioGroup(this);
        radioGroup.setMinimumHeight(Util.dp2px(44));
        radioGroup.setOrientation(RadioGroup.HORIZONTAL);
        ((ViewGroup)testBtn.getParent()).addView(scrollView);

        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                RetrofitServer.getListRepos();
//                RetrofitServer.getHomepage();
//                RetrofitServer.getHomepage();
//
//                LogUtils.e("OkHttpActivity", Thread.currentThread().getName());
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        LogUtils.e("OkHttpActivity", Thread.currentThread().getName());
//                        HomeServerApi.getConfigs("xcyo");
//                    }
//                }).start();
                createRadioGroup();
            }
        });
    }

    private void createRadioGroup(){
//        HorizontalScrollView scrollView = new HorizontalScrollView(this);
//
//        RadioGroup radioGroup = new RadioGroup(this);
//        radioGroup.setMinimumHeight(Util.dp2px(44));
//        radioGroup.setOrientation(RadioGroup.HORIZONTAL);
        radioGroup.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
            @Override
            public void onChildViewAdded(View parent, View child) {
                LogUtils.e("radioGroup","onViewAdd");
            }

            @Override
            public void onChildViewRemoved(View parent, View child) {
                LogUtils.e("radioGroup","onViewRemoved");
            }
        });
//        scrollView.addView(radioGroup);
//        radioGroup.setGravity(Gravity.CENTER_VERTICAL);

        for (int i = 0; i < 5;i++){
            RadioButton radioButton = createRadioButtonByTag("btn" + i);
            radioGroup.addView(radioButton);
        }
//        ((ViewGroup)testBtn.getParent()).addView(scrollView);
    }

    private RadioButton createRadioButtonByTag(String name) {
        Resources res = getResources();

        RadioButton radioButton = new RadioButton(this);
        radioButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        radioButton.setGravity(Gravity.CENTER);
        radioButton.setText(name);
        //左右padding
        int left = 30;
        //上下padding
        int top = 9;
        radioButton.setPadding(left, top, left, top);

        //左边距
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        radioButton.setLayoutParams(params);

        radioButton.setButtonDrawable(new ColorDrawable(0));
        radioButton.setSingleLine();
        radioButton.setTextColor(Color.parseColor("#323232"));
        return radioButton;
    }

    private String testOkHttp(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://www.xcyo.com").build();
        try {
//            Response response = client.newCall(request).execute();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    System.out.print(response.body().string());
                }
            });
//            System.out.println(response.body().string());
//            return response.body().toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEventOnUiThread(CustomEvent event) {
        testView.setText((String)event.getData());
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void handleEventOnBackground() {

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
