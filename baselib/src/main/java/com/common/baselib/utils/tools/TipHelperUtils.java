package com.common.baselib.utils.tools;

import android.app.Activity;
import android.app.Service;
import android.media.AudioManager;
import android.os.Vibrator;

import com.common.baselib.utils.PrefHelper;

/**
 * 震动提示 工具类
 * Created by Sudroid on 2018/2/6.
 */

public class TipHelperUtils {

    public static void Vibrate(final Activity activity, long milliseconds) {
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(milliseconds);
    }

    public static void Vibrate(final Activity activity, long[] pattern, boolean isRepeat) {

        AudioManager audioManager = (AudioManager) activity.getSystemService(Service.AUDIO_SERVICE);
        int mode = audioManager.getRingerMode();
        //静音模式下不震动
        if (mode != AudioManager.RINGER_MODE_SILENT && PrefHelper.getSoundStatus(activity)) {
            Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
            vib.vibrate(pattern, isRepeat ? 1 : -1);
        }

    }
}
