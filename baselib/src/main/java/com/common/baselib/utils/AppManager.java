package com.common.baselib.utils;

import android.app.Activity;
import android.content.Context;

import java.util.Enumeration;
import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 *
 * @version 1.0
 * @created 2014-10-14
 */
public class AppManager {

    private static final String TAG = AppManager.class.getSimpleName();

    private static Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = null;
        try {// 捕捉NoSuchElementException异常
            activity = activityStack.lastElement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activity;
    }

    /**
     * 获取倒数Activity
     * lastIndex  倒数第几个
     */
    public Activity lastIndexActivity(int lastIndex) {
        Activity activity = null;
        try {// 捕捉NoSuchElementException异常
            int size = activityStack.size();
            if (size - lastIndex > 0) {
                int nowSize = size - lastIndex;
                activity = activityStack.get(nowSize);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        try {// 捕捉NoSuchElementException异常
            Activity activity = activityStack.lastElement();
            finishActivity(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 移除当前Activity（堆栈中最后一个压入的）
     */
    public void removeActivity() {
        Activity activity = activityStack.lastElement();
        removeActivity(activity);
    }

    /**
     * 移除指定的Activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * 移除指定类名的Activity
     */
    public void removeActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                removeActivity(activity);
            }
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        //foreach 在7.0上存在安全问题，程序崩溃
        Enumeration<Activity> elements = activityStack.elements();
        if (elements.hasMoreElements()) {
            Activity activity = elements.nextElement();
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束顶部的所有Activity
     */
    public void finishAllButOne(Class<?> cls) {
        if (Utility.isEmpty(activityStack)) return;
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                if (activityStack.get(i).getClass().equals(cls)) continue;
                activityStack.get(i).finish();
                // finishActivity(activityStack.get(i));// Note：这种方式会出现数组越界，原因未知
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            //ActivityManager activityMgr= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            //activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}