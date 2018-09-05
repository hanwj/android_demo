package com.xiaoxiao.testrxjava;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xiaoxiao.testrxjava.actionBar.ActionBarActivity;
import com.xiaoxiao.testrxjava.chatlist.ChatListActivity;
import com.xiaoxiao.testrxjava.dagger2.DaggerMainActivityComponent;
import com.xiaoxiao.testrxjava.dagger2.MainActivityComponent;
import com.xiaoxiao.testrxjava.dagger2.User;
import com.xiaoxiao.testrxjava.lifecycle.LifecycleActivity;
import com.xiaoxiao.testrxjava.service.ServiceActivity;
import com.xiaoxiao.testrxjava.simplePagerTab.PagerSlidingTabActivity;
import com.xiaoxiao.testrxjava.testkeyboard.TestKeyBoardActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Map<String,Class<?>> funcMap = new HashMap<String,Class<?>>(){
        {
            put("toolbar",ToolbarActivity.class);
            put("actionbar",ActionBarActivity.class);
            put("anim",TestActivity.class);
            put("service", ServiceActivity.class);
            put("input", TestKeyBoardActivity.class);
            put("listview", ListViewInScrollActivity.class);
            put("surfaceview", SurfaceViewActivity.class);
            put("canvas",CanvasViewActivity.class);
            put("pagerSlidingTabStrip",PagerSlidingTabActivity.class);
            put("framelayout",FrameLayoutActivity.class);
            put("volley",VolleyActivity.class);
            put("testmix",TestMixActivity.class);
            put("slidingmenu",SlidingMenuActivity.class);
            put("butterKnift",TestButterKnifeActivity.class);
            put("okhttp", OkHttpActivity.class);
            put("lifecycle", LifecycleActivity.class);
            put("dragonAnim",DragonAnimActivity.class);
            put("chatList", ChatListActivity.class);
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
        component = DaggerMainActivityComponent.builder().build();
        component.inject(this);
        user.setName("caixiaoxiao");
        Log.e("MainActivity1", user.getName());
        Log.e("MainActivity1", user2.getName());
        user2.setName("caixiaoxiao2");
        Log.e("MainActivity1", user.getName());
//        FragmentManager manager = getFragmentManager();
//        android.support.v4.app.FragmentManager manager1 = getSupportFragmentManager();
//        Fragment frag;
//        frag.getFragmentManager();
//        frag.getParentFragment();
//        frag.getChildFragmentManager();
        MyTask task = new MyTask();
        task.execute("ddd");
        List<String> list = new ArrayList<>();
        for (int i = 0;i < 10;i++){
            list.add(i+"");
        }
        List<String> b = list.subList(0,10);
//        MutableLiveData<String> liveData = new MutableLiveData<>();
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
