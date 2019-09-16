package com.common.baselib.view.editview;

import android.text.method.PasswordTransformationMethod;
import android.view.View;

/**
 * 自定义控件隐藏字符显示符号
 * Created by Sudroid on 2019/4/11.
 */
public class ShowReturnsTransformationMethod extends PasswordTransformationMethod {

    private ShowReturnsTransformationMethod() {

    }

    private static class Instance {
        private static final ShowReturnsTransformationMethod INSTANCE = new ShowReturnsTransformationMethod();
    }

    public static ShowReturnsTransformationMethod getInstance() {
        return Instance.INSTANCE;
    }


    @Override
    public CharSequence getTransformation(CharSequence source, View view) {
        return new PasswordCharSequence(source);
    }

    private class PasswordCharSequence implements CharSequence {
        private CharSequence mSource;

        private PasswordCharSequence(CharSequence source) {
            mSource = source;
        }

        public char charAt(int index) {
            return '*';
        }

        public int length() {
            return mSource.length();
        }

        public CharSequence subSequence(int start, int end) {
            return mSource.subSequence(start, end);
        }
    }
}

