package com.polamrapps.imageloader;

import android.util.Log;

/**
 * Created by Rajareddy on 17/06/16.
 */
public class Utils {
    private static String TAG = "PolamR";
    private static boolean show = true;

    public static void show(String msg) {
        if(show)
            Log.i(TAG, msg);
    }
}

