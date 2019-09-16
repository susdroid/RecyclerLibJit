package com.common.baselib;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

import com.common.baselib.widget.YcRefreshHeader;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;
import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.unit.Subunits;

/**
 * Created by Sudroid on 2019/3/25.
 */
public class BaseApplication extends Application {

    public static int sWidth;
    public static int sHeight;
    public static float sScale;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化自动适配
        configUnits();

        context = getApplicationContext();
        DisplayMetrics dm = getResources().getDisplayMetrics();
        sScale = dm.density;
        sWidth = dm.widthPixels;
        sHeight = dm.heightPixels;
        //初始化日志
        initLog();
        //默认刷新头部框架
        initRefresh();
    }

    private void initLog() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(3)        // (Optional) Skips some method invokes in stack trace. Default 5
                .tag("Sudroid")   // (Optional) Custom tag for each log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }

    private void configUnits() {
        AutoSizeConfig.getInstance().getUnitsManager()
                .setSupportDP(false)
                .setSupportSubunits(Subunits.MM);
    }

    //初始化全局refresh header
    private void initRefresh() {

        // 指定全局的下拉Header
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((DefaultRefreshHeaderCreator) (context, layout) -> new YcRefreshHeader(context));
    }

    // 调用
    public static Context getContext() {
        return context;
    }


    //dex 突破方法限制
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
