package com.xiaoxiao.testrxjava;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xiaoxiao.testrxjava.actionBar.ActionBarActivity;
import com.xiaoxiao.testrxjava.chatlist.ChatListActivity;
import com.xiaoxiao.testrxjava.daemon.DaemonActivity;
import com.xiaoxiao.testrxjava.dagger2.DaggerMainActivityComponent;
import com.xiaoxiao.testrxjava.dagger2.MainActivityComponent;
import com.xiaoxiao.testrxjava.dagger2.User;
import com.xiaoxiao.testrxjava.floatwindow.MockClickWindow;
import com.xiaoxiao.testrxjava.floatwindow.permission.FloatWindowManager;
import com.xiaoxiao.testrxjava.lifecycle.LifecycleActivity;
import com.xiaoxiao.testrxjava.service.ServiceActivity;
import com.xiaoxiao.testrxjava.simplePagerTab.PagerSlidingTabActivity;
import com.xiaoxiao.testrxjava.testkeyboard.TestKeyBoardActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import javax.inject.Inject;


/**
 * Created by caixiaoxiao on 27/5/16.
 */
public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    public static MainActivityComponent component;
    @Inject
    User user;
    @Inject
    User user2;

    private List<Pair<String,Class<?>>> funcList = new ArrayList<Pair<String,Class<?>>>(){
        {
            add(new Pair<String, Class<?>>("toolbar",ToolbarActivity.class));
            add(new Pair<String, Class<?>>("actionbar",ActionBarActivity.class));
            add(new Pair<String, Class<?>>("anim",TestActivity.class));
            add(new Pair<String, Class<?>>("service", ServiceActivity.class));
            add(new Pair<String, Class<?>>("input", TestKeyBoardActivity.class));
            add(new Pair<String, Class<?>>("listview", ListViewInScrollActivity.class));
            add(new Pair<String, Class<?>>("surfaceview", SurfaceViewActivity.class));
            add(new Pair<String, Class<?>>("canvas",CanvasViewActivity.class));
            add(new Pair<String, Class<?>>("pagerSlidingTabStrip",PagerSlidingTabActivity.class));
            add(new Pair<String, Class<?>>("framelayout",FrameLayoutActivity.class));
            add(new Pair<String, Class<?>>("volley",VolleyActivity.class));
            add(new Pair<String, Class<?>>("testmix",TestMixActivity.class));
            add(new Pair<String, Class<?>>("slidingmenu",SlidingMenuActivity.class));
            add(new Pair<String, Class<?>>("butterKnift",TestButterKnifeActivity.class));
            add(new Pair<String, Class<?>>("okhttp", OkHttpActivity.class));
            add(new Pair<String, Class<?>>("lifecycle", LifecycleActivity.class));
            add(new Pair<String, Class<?>>("dragonAnim",DragonAnimActivity.class));
            add(new Pair<String, Class<?>>("chatList", ChatListActivity.class));
            add(new Pair<String, Class<?>>("floatwindow",null));
            add(new Pair<String, Class<?>>("webview",WebActivity.class));
            add(new Pair<String, Class<?>>("DaemonService", DaemonActivity.class));
            add(new Pair<String, Class<?>>("plugin", PluginActivity.class));
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewGroup rootView = (ViewGroup)findViewById(R.id.root_view);
        for (Pair<String,Class<?>> pair : funcList){
            Button button = new Button(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(30, 30, 30, 30);
            button.setLayoutParams(layoutParams);
            button.setText(pair.first);
            button.setBackgroundResource(R.drawable.bg_video_info);
            rootView.addView(button);

            if ("floatwindow".equals(pair.first)){
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (FloatWindowManager.getInstance().checkPermission(MainActivity.this)){
                            MockClickWindow.getInstance().show(MainActivity.this.getApplicationContext());
                        }else {
                            FloatWindowManager.getInstance().applyPermission(MainActivity.this);
                        }
                    }
                });
            }else {
                final Class<?> cls = pair.second;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,cls);
                        startActivity(intent);
                    }
                });
            }
        }
        component = DaggerMainActivityComponent.builder().build();
        component.inject(this);
        user.setName("caixiaoxiao");
        Log.e("MainActivity1", user.getName());
        Log.e("MainActivity1", user2.getName());
        user2.setName("caixiaoxiao2");
        Log.e("MainActivity1", user.getName());

        MyTask task = new MyTask();
        task.execute("ddd");
        List<String> list = new ArrayList<>();
        for (int i = 0;i < 10;i++){
            list.add(i+"");
        }
        List<String> b = list.subList(0,10);
    }

    @Deprecated
    @SuppressWarnings("")
    public void testAnnatation(){
        getClass().getAnnotation(SuppressWarnings.class).value();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("MainActivity1", user.getName());
        Log.e(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.e(TAG,"onSaveInstanceState1");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG,"onSaveInstanceState2");
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG,"onRestoreInstanceState2");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"destory");
    }

    @Override
    public void finish() {
        super.finish();
        Log.e(TAG,"finish");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    private class MyTask extends AsyncTask<String,Integer,Boolean>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }
}
