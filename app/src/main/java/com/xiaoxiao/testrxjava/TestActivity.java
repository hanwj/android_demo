package com.xiaoxiao.testrxjava;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BulletSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

//import com.android.volley.toolbox.Volley;
//import com.xiaoxiao.testrxjava.dagger2.DaggerTestActivityComponent;
import com.xiaoxiao.testrxjava.dagger2.DaggerMainActivityComponent;
import com.xiaoxiao.testrxjava.dagger2.User;
import com.xiaoxiao.testrxjava.databinding.ActivityTestBinding;
import com.xiaoxiao.utils.FastBlur;
import com.xiaoxiao.utils.LogUtils;
import com.xiaoxiao.utils.Util;
import com.xiaoxiao.view.MyCircleView;
import com.xiaoxiao.view.VerticalCenterImageSpan;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import javax.inject.Inject;

/**
 * Created by meibo-design on 17/5/16.
 */
public class TestActivity extends AppCompatActivity {
    @Inject
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        final TextView txtView = (TextView) findViewById(R.id.test_textview);
        final View scrollView = findViewById(R.id.test_scroll_text);
        Button scaleBtn = (Button) findViewById(R.id.scale_btn);
        Button translateBtn = (Button) findViewById(R.id.translate_btn);
        Button scroll1 = (Button) findViewById(R.id.scroll1);
        Button scroll2 = (Button) findViewById(R.id.scroll2);
        Button scroll3 = (Button) findViewById(R.id.scroll3);
        final Animation scaleAnim = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        final Animation moveAnim = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.scale_btn) {
                    txtView.startAnimation(scaleAnim);
                } else if (id == R.id.translate_btn) {
                    txtView.startAnimation(moveAnim);
                } else if (id == R.id.scroll1) {
                    scrollView.scrollTo(100, 0);
                } else if (id == R.id.scroll2) {
                    scrollView.scrollTo(0, 100);
                } else if (id == R.id.scroll3) {
                    scrollView.setScrollX(0);
                    scrollView.setScrollY(0);
                }
            }
        };
        scaleBtn.setOnClickListener(listener);
        translateBtn.setOnClickListener(listener);
        scroll1.setOnClickListener(listener);
        scroll2.setOnClickListener(listener);
        scroll3.setOnClickListener(listener);


        TextView richText = (TextView) findViewById(R.id.rich_text);
        Spannable span1 = new SpannableString("测试测试");

        Spannable span2 = new SpannableString("图片");
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_delete);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan imageSpan = new ImageSpan(drawable);
        span2.setSpan(imageSpan, 0, span2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        Spannable span3 = new SpannableString("图片2");
        span3.setSpan(new ImageSpan(drawable), 0, span3.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        Spannable span4 = new SpannableString(" 小号字体 ");
        span4.setSpan(new BulletSpan(),0,span4.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        span4.setSpan(new AbsoluteSizeSpan(Util.sp2px(12)),0,span4.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        span4.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),0,span4.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(span1).append(span4).append(span2).append(span3);
        richText.setText(builder);

        MyCircleView circleView = (MyCircleView) findViewById(R.id.test_circle);
        circleView.setRadius(90);
        circleView.setColor(Color.BLUE);
        MainActivity.component.provideTestComponent().inject(this);
//        DaggerMainActivityComponent.builder().build().provideTestComponent().inject(this);
//        DaggerTestActivityComponent.builder().build().inject(this);
        Log.e("TestActvity1", user.getName());
        user.setName("test");
        Log.e("TestActvity2", user.getName());

        final View blurTestView = findViewById(R.id.test_blur);
        blurTestView.postDelayed(new Runnable() {
            @Override
            public void run() {
                Drawable bgDrawable = blurTestView.getBackground();
                int w = bgDrawable.getBounds().width();
                int h = bgDrawable.getBounds().height();
                Bitmap.Config config = bgDrawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565;
                Bitmap bp = Bitmap.createBitmap(w, h, config);
                Canvas canvas = new Canvas(bp);
                bgDrawable.setBounds(0, 0, w, h);
                bgDrawable.draw(canvas);
                canvas.save();
//                bp = FastBlur.blur(TestActivity.this, bp, 25.0f);
                bp = FastBlur.doBlurByRenderScript(TestActivity.this, bp, 25.0f);
                blurTestView.setBackgroundDrawable(new BitmapDrawable(bp));
                txtView.setBackgroundDrawable(new BitmapDrawable(bp));
            }
        }, 1000);

        test();

        String encryptStr = encryptStr("eqre123232132121321");
        encryptStr(encryptStr);
        bitOperate();
    }

    private void test() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE},12);
            return;
        }
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String imei = tm.getImei();
            String meid = tm.getMeid();
            LogUtils.e("telehony","imei:" + imei);
            LogUtils.e("telehony","meid:" + meid);
        }
        LogUtils.e("telehony","deviceId:" + deviceId);
        String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        LogUtils.e("telehony","androidId:" + androidId);
    }

    private String encryptStr(String str){
        if (TextUtils.isEmpty(str)){
            return "";
        }
        char[] chars = str.toCharArray();
        for (int i = 0;i < chars.length;i++){
            chars[i] = (char)((int)chars[i]^200);
        }
        String encryptStr = new String(chars);
        LogUtils.e("TestActivity","encrypt:" + encryptStr);
        return encryptStr;
    }

    private void bitOperate(){
        int result = 0;
        int[] arrs = new int[]{1,0,1,0,0,0,1,0};
        int length = arrs.length;
        for (int i = 0;i < length;i++){
            result = result + arrs[i];
            if (i != length-1){
                result = result << 1;
            }
        }
        LogUtils.e("TestActivity",result + "");
        String binaryStr = Integer.toBinaryString(result);
        LogUtils.e("TestActivity",Integer.toBinaryString(result));

        result = Integer.parseInt(binaryStr,2);
        LogUtils.e("TestActivity",result + "");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void test(Class clz){
    }
}
