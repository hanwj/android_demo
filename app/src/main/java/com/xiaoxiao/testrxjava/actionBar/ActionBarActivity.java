package com.xiaoxiao.testrxjava.actionBar;

import android.app.ActionBar;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaoxiao.testrxjava.R;

/**
 * Created by caixiaoxiao on 23/12/16.
 */
public class ActionBarActivity extends FragmentActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        SpinnerAdapter mSpinnerAdapter = new ArrayAdapter<CharSequence>(
                this,android.R.layout.simple_spinner_dropdown_item,new String[]{"支付","红包","朋友"});
        ActionBar.OnNavigationListener mOnNavigationListener = new ActionBar.OnNavigationListener() {
            @Override
            public boolean onNavigationItemSelected(int itemPosition, long itemId) {
                return true;
            }
        };
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actionBar.setListNavigationCallbacks(mSpinnerAdapter, mOnNavigationListener);
        setContentView(R.layout.activity_action_bar);

        final EditText et1 = findViewById(R.id.et_str1);
        final EditText et2 = findViewById(R.id.et_str2);
        final TextView resultTv = findViewById(R.id.tv_result);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str1 = et1.getText().toString();
                String str2 = et2.getText().toString();
                if (TextUtils.isEmpty(str1) || TextUtils.isEmpty(str2)){
                    resultTv.setText("比较结果:");
                }else {
                    int result = str1.compareTo(str2);
                    String resultStr = result > 0 ? ">" : result==0 ? "=" : "<";
                    resultTv.setText(String.format("比较结果:%s %s %s",str1,resultStr,str2));
                }
            }
        };
        et1.addTextChangedListener(watcher);
        et2.addTextChangedListener(watcher);

        String imgUrl = "http://v.img.pplive.cn/2b/e3/2be31f7559bfa76461f962f6a897f810/1575275039346.jpg";
        SimpleDraweeView img1 = findViewById(R.id.img_test1);
        img1.setImageURI(imgUrl);

        SimpleDraweeView img2 = findViewById(R.id.img_test2);
        GenericDraweeHierarchy hierarchy = img2.getHierarchy();
        if (hierarchy == null)
        {
            GenericDraweeHierarchyBuilder hierarchyBuilder = new GenericDraweeHierarchyBuilder(getResources());
            hierarchy = hierarchyBuilder.build();
            img2.setHierarchy(hierarchy);
        }
        hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.FOCUS_CROP);
        hierarchy.setActualImageFocusPoint(new PointF(0,0));
        img2.setImageURI(imgUrl);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_options,menu);

        return super.onCreateOptionsMenu(menu);
    }
}
