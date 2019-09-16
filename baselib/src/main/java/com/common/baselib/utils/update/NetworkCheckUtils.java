package com.common.baselib.utils.update;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络检测工具
 * Created by caojing on 16/8/3.
 */
public class NetworkCheckUtils {

    private static NetworkCheckUtils instance;

    public static NetworkCheckUtils getInstance() {
        if (instance == null) {
            instance = new NetworkCheckUtils();
        }
        return instance;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
        } else {
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
