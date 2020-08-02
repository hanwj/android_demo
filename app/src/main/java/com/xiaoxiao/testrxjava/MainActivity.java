package com.xiaoxiao.testrxjava;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v4.content.LocalBroadcastManager;
import android.os.Process;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xiaoxiao.entity.UserInfo;
import com.xiaoxiao.testrxjava.actionBar.ActionBarActivity;
import com.xiaoxiao.testrxjava.chatlist.ChatListActivity;
import com.xiaoxiao.testrxjava.coordinatorlayout.CoordinatorLayoutActivity;
import com.xiaoxiao.testrxjava.daemon.DaemonActivity;
import com.xiaoxiao.testrxjava.dagger2.DaggerMainActivityComponent;
import com.xiaoxiao.testrxjava.dagger2.MainActivityComponent;
import com.xiaoxiao.testrxjava.dagger2.User;
import com.xiaoxiao.testrxjava.databinding.ActivityMainBinding;
import com.xiaoxiao.testrxjava.floatwindow.MockClickWindow;
import com.xiaoxiao.testrxjava.floatwindow.permission.FloatWindowManager;
import com.xiaoxiao.testrxjava.lifecycle.LifecycleActivity;
import com.xiaoxiao.testrxjava.service.ServiceActivity;
import com.xiaoxiao.testrxjava.simplePagerTab.PagerSlidingTabActivity;
import com.xiaoxiao.testrxjava.testkeyboard.TestKeyBoardActivity;
import com.xiaoxiao.utils.LogUtils;
import com.xiaoxiao.utils.Util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
            add(new Pair<String, Class<?>>("coordinator", CoordinatorLayoutActivity.class));
            add(new Pair<String, Class<?>>("recyclerview", RecyclerViewActivity.class));
            add(new Pair<String,Class<?>>("openThirdApp",null));
            add(new Pair<String, Class<?>>("kill",null));
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG,"onCreate");
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
            } else if ("openThirdApp".equals(pair.first)){
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        String link = "pptv://page/player/halfscreen?type=live&sectionid=166433";
//                        String link = "pptv://page/cate/vine?name=%E5%BD%B1%E8%A7%86&vid=29325143";
//                        String link = "pptv://page/player/halfscreen?type=live&sectionid=175081&utm=6211";
                        String link = "suning://m.suning.com/index?utm_source=jrtt-zt&utm_medium=aj-tx1&utm_campaign=&adTypeCode=1002&adId=https%3A%2F%2Fc.m.suning.com%2Fphone2019.html&backurl=__back_url__&wap_compagin=&wap_content=&wap_medium=17732&wap_source=%25E6%2596%25B0%25E8%25A1%2597%25E5%258F%25A3%25E5%2588%25B8%25E6%25B4%25BB%25E5%258A%25A8%25E6%25BB%25A1%25E5%2587%258F&wap_term=&adId=https%3a%2f%2fc.m.suning.com%2fsnWhale.html%3fwx_navbar_transparent%3dtrue%23%2f";
//                        String link = "pptv://page/cate/vine?name=%E5%BD%B1%E8%A7%86";
//                        String link = "pptv://page/cate/vine";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
            } else if ("kill".equals(pair.first)){
              button.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
//                      System.exit(0);
                      Process.killProcess(Process.myPid());
                  }
              });
            } else {
                final Class<?> cls = pair.second;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.gc();
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

        boolean test = (true || false) && false;
        Log.e("MainActivity1","test:" + test);

        int pos = 2;
        Locale[] locales = new Locale[]{Locale.getDefault(),Locale.CHINA,Locale.CHINESE,Locale.ENGLISH,Locale.FRANCE,Locale.JAPAN,Locale.KOREA,Locale.ROOT};
        for (int i = 0; i < locales.length;i++){
            String formatStr = String.format(locales[i],"%02d",pos);
            LogUtils.e("MainActivity","locale--" + locales[i] + "--format--" + formatStr);
        }

        UserInfo userInfo = new UserInfo("111","caixiaoxiao","xxx");
        String str = Base64.encodeToString(serializeObj(userInfo),0);
        deserializeObj(Base64.decode(str,0));

        Util.test(this);
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
//        LocalBroadcastManager.getInstance()
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
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");
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
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        Log.e(TAG,"onMultiWindowModeChanged:" + isInMultiWindowMode);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e(TAG,"onConfigurationChanged");
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
            return false;
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

    private byte[] serializeObj(Object obj){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream objOut = null;
        try {
            objOut = new ObjectOutputStream(out);
            objOut.writeObject(obj);
            LogUtils.e("MainActivity--serializeObj",out.toString("UTF-8"));
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objOut != null){
                try {
                    objOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private Object deserializeObj(byte[] data){
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream objIn = null;
        try {
            objIn = new ObjectInputStream(in);
            Object user = objIn.readObject();
            LogUtils.e("MainActivity--deserializeObj",user.toString());
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (objIn != null){
                try {
                    objIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private byte[] parcelObj(Parcelable parcelable){
        Parcel parcel = Parcel.obtain();
        parcel.setDataPosition(0);
        parcelable.writeToParcel(parcel,0);
        byte[] bytes = parcel.marshall();
        parcel.recycle();
        return bytes;
    }
}
