/*
 * Copyright 2013 By Jcdroid
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.common.baselib.view.editview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.method.HideReturnsTransformationMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.common.baselib.R;
import com.common.baselib.utils.Utility;

import androidx.appcompat.widget.AppCompatEditText;


/**
 * 带清除按钮输入框， 在焦点变化时和输入内容发生变化时均要判断是否显示右边clean图标
 */
public class ShowHideEditText extends AppCompatEditText {

    private Drawable mRightDrawable;
    private boolean isShow = true;

    public ShowHideEditText(Context context) {
        super(context);
        init();
    }

    public ShowHideEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShowHideEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        Drawable[] drawables = this.getCompoundDrawables();

        mRightDrawable = drawables[2];

        initRightDra();
    }

    /**
     * 设置点击事件
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:

                boolean isClick = (event.getX() > (getWidth() - getTotalPaddingRight())) &&
                        (event.getX() < (getWidth() - getPaddingRight()));
                if (isClick) {
                    if (isShow) {
                        isShow = false;
                        setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_eyes_gray, 0);
                        this.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    } else {
                        isShow = true;
                        setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_eyes_close_gray, 0);
                        this.setTransformationMethod(ShowReturnsTransformationMethod.getInstance());
                    }
                    this.setSelection(Utility.getTextString(this).length());
                }
                break;

            default:
                break;
        }
        return super.onTouchEvent(event);
    }


    /**
     * 隐藏或者显示右边clean的图标
     */
    protected void initRightDra() {
        Drawable rightDrawable;
        rightDrawable = mRightDrawable;
        //使用代码设置该控件left, top, right, and bottom处的图标
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1],
                rightDrawable, getCompoundDrawables()[3]);

        this.setTransformationMethod(ShowReturnsTransformationMethod.getInstance());
    }
}

