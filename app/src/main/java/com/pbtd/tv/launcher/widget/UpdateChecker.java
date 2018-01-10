package com.pbtd.tv.launcher.widget;

import android.content.Context;
import android.util.Log;

import com.pbtd.tv.launcher.constant.Constant;

public class UpdateChecker {


    public static void checkForDialog(Context context) {
        if (context != null) {
            new CheckUpdateTask(context, Constant.TYPE_DIALOG, true).execute();
        } else {
            Log.e(Constant.TAG, "The arg context is null");
        }
    }


    public static void checkForNotification(Context context) {
        if (context != null) {
            new CheckUpdateTask(context, Constant.TYPE_NOTIFICATION, false).execute();
        } else {
            Log.e(Constant.TAG, "The arg context is null");
        }

    }


}
