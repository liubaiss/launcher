package com.pbtd.tv.launcher.utils;

import android.util.Log;

/**
 * Created by gong on 2017/9/8.
 */

public class LogUtil {
    public static final Boolean DEBUG_MODE = true;

    public static void d(String tag,String message) {
        if (DEBUG_MODE) {
            Log.d(tag, message);
        }
    }
}
