package com.xiaoxiao.testrxjava;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiaoxiao.eventbus.CustomEvent;
import com.xiaoxiao.eventbus.IEventBusReceiver;
import com.xiaoxiao.retrofit.RetrofitServer;
import com.xiaoxiao.server.HomeServerApi;
import com.xiaoxiao.utils.LogUtils;

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
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitServer.getListRepos();
                RetrofitServer.getHomepage();
                RetrofitServer.getHomepage();

                LogUtils.e("OkHttpActivity", Thread.currentThread().getName());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LogUtils.e("OkHttpActivity", Thread.currentThread().getName());
                        HomeServerApi.getConfigs("xcyo");
                    }
                }).start();
            }
        });
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
