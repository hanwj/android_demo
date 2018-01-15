package com.xiaoxiao.view.dragon;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.funzio.pure2D.BaseScene;
import com.funzio.pure2D.BaseStage;
import com.funzio.pure2D.gl.GLColor;
import com.funzio.pure2D.gl.gl10.textures.Texture;
import com.funzio.pure2D.gl.gl10.textures.TextureOptions;
import com.funzio.pure2D.lwf.LWF;
import com.funzio.pure2D.lwf.LWFData;
import com.funzio.pure2D.lwf.LWFManager;
import com.funzio.pure2D.lwf.LWFObject;
import com.xiaoxiao.testrxjava.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caixiaoxiao on 1/1/18.
 */

public class DragonLwfView extends FrameLayout{

    private static final int ANIM_END = 1; //动画结束
    private static final int LOAD_ERROR = 2; //加载出错
    private static final int LOAD_NEXT = 3; //加载下一个动画

    private BaseStage mStage;
    private BaseScene mScene;
    private LWFManager mLWFManager;

    private List<LWFObject> mLwfObjects;
    private List<String> mList;
    private int num; //动画数量
    private int maxNum = 1; //最大动画数量
    float lwfScale = 1;
    public DragonLwfView(@NonNull Context context) {
        super(context);
        init();
    }

    public DragonLwfView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DragonLwfView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.layout_dragon,this,true);
        mStage = (BaseStage) findViewById(R.id.dragon_stage);
        //初始化lwf
        mStage.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        mStage.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        mStage.setZOrderOnTop(true);
        mScene = new BaseScene();
        mScene.setColor(new GLColor(0f,0f,0f,0f));
        mStage.setScene(mScene);
        mLWFManager = new LWFManager();

        mLwfObjects = new ArrayList<>();
        mList = new ArrayList<>();
        lwfScale = getResources().getDisplayMetrics().density / 2;
    }

    public void addNotification(String item){
        if (item != null){
            mList.add(item);
        }
        attachOneLwf();
    }

    public void attachOneLwf(){
        if (num >= maxNum || mList.size() <= 0){
            return;
        }
        ++num;
        String next = mList.remove(0);
        mScene.queueEvent(new Runnable() {
            @Override
            public void run() {
                final LWFObject lwfObject = new LWFObject();
                LWFData lwfData = getLWFData();
                if (lwfData != null){
                    final LWF lwf = lwfObject.attachLWF(lwfData);
                    mScene.addChild(lwfObject);
                    mLwfObjects.add(lwfObject);
                    lwf.scale("_root",lwfScale,lwfScale);
                    DragonLwfView.this.post(new Runnable() {
                        @Override
                        public void run() {
                            setAnimatorSet(lwfObject,lwf);
                        }
                    });
                }
            }
        });
    }

    private void setAnimatorSet(final LWFObject lwfObject,final LWF lwf){
        if (lwfObject == null || lwf == null){
            return;
        }
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        float lwfWidth = lwf.getSize().x * lwfScale;
        Log.e("DragonLwfView","lwf's width:" + lwfWidth);
        ValueAnimator animator = ValueAnimator.ofFloat(screenWidth,-lwfWidth);
        animator.setDuration(12000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                lwf.moveTo("_root",value,-getResources().getDisplayMetrics().heightPixels);
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                handleMessage(ANIM_END,lwfObject);
            }
        });
        animator.start();
    }

    private void removeLwf(LWFObject lwfObject){
        if (mScene != null && lwfObject != null){
            mScene.removeChild(lwfObject);
            if (mScene.getTextureManager() != null){
//                Texture[] textures = lwfObject.getTextures();
//                if (textures != null){
//                    for (int i = 0; i < textures.length; i++){
//                        mScene.getTextureManager().removeTexture(textures[i]);
//                    }
//                }
            }
        }
        if (lwfObject != null && mLwfObjects.contains(lwfObject)){
            mLwfObjects.remove(lwfObject);
        }
        if (mLwfObjects.size() == 0 && this.mLWFManager != null){
            mLWFManager.dispose();
        }
    }

    private void removeAllLwf(){
        if (mScene != null){
            mScene.removeAllChildren();
            if (mScene.getTextureManager() != null){
                mScene.getTextureManager().removeAllTextures();
            }
        }
        mLwfObjects.clear();
        if (mLWFManager != null){
            mLWFManager.dispose();
        }
    }

    private LWFData getLWFData(){
        String lwfTexturePath = "shenlongnotification"; //绝对路径
        String lwfPath = "shenlongnotification/shenlongnotification.lwf";
        InputStream stream = null;
        try {
            stream = getContext().getAssets().open(lwfPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (stream != null){
            try {
                LWFData lwfData = mLWFManager.createLWFData(stream);
                int textureNum = lwfData.getTextureNum();
                Texture[] textures = new Texture[textureNum];
                for (int i = 0; i < textureNum; ++i){
                    String name = lwfData.getTextureName(i);
                    if (textures[i] == null){
                        String texturePath = lwfTexturePath + File.separator + name;
                        textures[i] = mScene.getTextureManager().createAssetTexture(texturePath,null);
                    }
                }
                lwfData.setTextures(textures);
//                if (lwfObject != null){
//                    lwfObject.setTextures(textures);
//                }
                return lwfData;
            } catch (Exception e){
                e.printStackTrace();
            }
        } else {

        }
        return null;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        release();
    }

    private void release(){
        mList.clear();
        mScene.queueEvent(new Runnable() {
            @Override
            public void run() {
                removeAllLwf();
            }
        });
    }

    private void handleMessage(final int code, final Object what){
        post(new Runnable() {
            @Override
            public void run() {
                switch(code){
                    case LOAD_ERROR:
                    case ANIM_END:
                        //上个动画加载失败或者是播放结束
                        --num;
                        if (what != null && what instanceof LWFObject){
                            mScene.queueEvent(new Runnable() {
                                @Override
                                public void run() {
                                    removeLwf((LWFObject) what);
                                }
                            });
                        }
                    case LOAD_NEXT:
                        //加载下一个动画
                        attachOneLwf();
                        break;
                }
            }
        });
    }
}
