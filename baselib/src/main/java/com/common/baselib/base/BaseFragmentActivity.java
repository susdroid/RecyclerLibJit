package com.common.baselib.base;


import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.baselib.R;
import com.common.baselib.R2;
import com.common.baselib.utils.AntiShake.AntiShake;
import com.common.baselib.utils.AppManager;
import com.common.baselib.utils.statusbar.StatusBarUtils;
import com.common.baselib.view.layout.SlidingLayout;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import java.lang.reflect.Field;
import java.util.List;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import butterknife.BindView;

public class BaseFragmentActivity extends RxAppCompatActivity {

    protected static String CLASS_NAME;

    @Nullable
    @BindView(R2.id.toolbar_title)
    public TextView mTitleView;

    @Nullable
    @BindView(R2.id.toolbar)
    public Toolbar mToolbar;

    @Nullable
    @BindView(R2.id.bar_line)
    public View mLine;

    @Nullable
    @BindView(R2.id.my_content_view)
    public LinearLayout mContentView;

    private BroadcastReceiver finishReceiver;//关闭应用广播
    private String filterActionTag = "finish";//关闭应用广播标签

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        CLASS_NAME = getClass().getSimpleName();
        clearStatusColor();
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //侧滑
        if (enableSlideClose()) {
            SlidingLayout rootView = new SlidingLayout(this);
            rootView.bindActivity(this);
        }
        //添加到activity 堆栈管理
        AppManager.getAppManager().addActivity(this);

        //toolBar
        if (mToolbar != null && mTitleView != null) {
            setSupportActionBar(mToolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                actionBar.setDisplayHomeAsUpEnabled(false);
                actionBar.setDisplayShowTitleEnabled(false);
            }
        }
        //设置状态栏透明
        StatusBarUtils.setTranslucentStatus(this);

        IntentFilter localIntentFilter = new IntentFilter();
        localIntentFilter.addAction(filterActionTag);
        registerReceiver(getFinishReceiver(), localIntentFilter);

    }

    /**
     * 解决Android7.0下沉浸式状态栏变灰问题
     * 要在setContentView方法前调用
     */
    private void clearStatusColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                Class decorViewClazz = Class.forName("com.android.internal.policy.DecorView");
                Field field = decorViewClazz.getDeclaredField("mSemiTransparentStatusBarColor");
                field.setAccessible(true);
                field.setInt(getWindow().getDecorView(), Color.TRANSPARENT);  //改为透明
            } catch (Exception e) {
            }
        }
    }

    protected void setFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitAllowingStateLoss();
    }

    //控制左滑页面关闭页面
    public boolean enableSlideClose() {
        return true;
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        if (mTitleView != null) {
            mTitleView.setText(title);
        }
        if (mToolbar != null) {
            mToolbar.setTitle("");
        }
    }

    protected void setLeftIcon(@DrawableRes int icon) {
        setLeftIcon(icon, v -> {
            if (AntiShake.check(v.getId())) return;
            setResult(Activity.RESULT_OK);
            finish();
        });
    }

    protected void setLeftIcon(@DrawableRes int icon, View.OnClickListener listener) {
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(icon);
            mToolbar.setNavigationOnClickListener(listener);
        }
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_OK);
        finish();
        super.onBackPressed();
    }

    //限定字体大小不修改
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }


    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
    }


    /***
     * 初始化广播
     *
     * @return
     */
    public BroadcastReceiver getFinishReceiver() {
        if (finishReceiver == null) {
            finishReceiver = new BroadcastReceiver() {
                public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent) {
                    if (filterActionTag.equals(paramAnonymousIntent.getAction())) {
                        BaseFragmentActivity.this.finish();
                    }
                }
            };
        }
        return finishReceiver;
    }

    /**
     * 程序是否在前台运行
     *
     * @return
     */
    public boolean isAppOnForeground() {
        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();
        List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        for (RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }


    //点击空白处隐藏软键盘
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        try {
            return super.dispatchTouchEvent(ev);
        } catch (Exception ex) {
            return false;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            return super.onTouchEvent(event);
        } catch (Exception ex) {
        }
        return false;
    }

    private boolean isShouldHideKeyboard(View v, MotionEvent event) {

        if (v != null && (v instanceof EditText)) {

            int[] l = {0, 0};

            v.getLocationInWindow(l);

            int left = l[0],

                    top = l[1],

                    bottom = top + v.getHeight(),

                    right = left + v.getWidth();

            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;

            } else {
                return true;
            }
        }
        return false;
    }

    private void hideKeyboard(IBinder token) {

        if (token != null) {

            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);

        }

    }

    @Override
    protected void onDestroy() {
        AppManager.getAppManager().removeActivity(this);
        unregisterReceiver(this.finishReceiver);
        super.onDestroy();
    }
}
