package com.xiaoxiao.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.xiaoxiao.testrxjava.R;

public class ClearEditText extends EditText {
    private Drawable clearDrawable;

    public ClearEditText(Context context) {
        this(context,null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs,android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        clearDrawable = getCompoundDrawables()[2];
        if (clearDrawable == null){
            clearDrawable = getResources().getDrawable(R.mipmap.ic_delete);
        }
        setClearIconVisible(false); //默认隐藏
//        setOnFocusChangeListener(this);
//        addTextChangedListener(this);
    }

    private void setClearIconVisible(boolean visible){
        Drawable right = visible ? clearDrawable : null;
        setCompoundDrawablesWithIntrinsicBounds(getCompoundDrawables()[0],getCompoundDrawables()[1],
                right,getCompoundDrawables()[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_UP:
                Drawable right = getCompoundDrawables()[2];
                if (right != null && event.getX() <= (getWidth() - getPaddingRight())
                        && (event.getX() >= (getWidth() - getPaddingRight() - right.getBounds().width()))){
                    setText("");
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        setClearIconVisible(focused && length() > 0); //焦点改变
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        setClearIconVisible(hasFocus() && length() > 0); //文本变更
    }


}
