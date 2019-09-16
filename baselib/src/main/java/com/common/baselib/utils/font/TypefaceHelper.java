package com.common.baselib.utils.font;

import android.content.Context;
import android.graphics.Typeface;

import androidx.collection.SimpleArrayMap;

/**
 * 解析字体内存优化  字体缓存工具类
 * 参考 https://www.jianshu.com/p/d75418a38500
 * Created by Sudroid on 2018/7/19.
 */

public class TypefaceHelper {

    private static final String TAG = "TypefaceHelper";
    private static final SimpleArrayMap<String, Typeface> TYPEFACE_CACHE = new SimpleArrayMap<String, Typeface>();

    public static Typeface get(Context context, String name) {
        synchronized (TYPEFACE_CACHE) {
            if (!TYPEFACE_CACHE.containsKey(name)) {

                try {
                    Typeface t = Typeface.createFromAsset(context.getAssets(), name);
                    TYPEFACE_CACHE.put(name, t);
                } catch (Exception e) {
                    return null;
                }
            }
            return TYPEFACE_CACHE.get(name);
        }
    }

}
