package com.android.tvnet.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.DisplayMetrics;

import com.android.tvnet.activity.SplashActivity;

import java.util.Locale;

public class PhoneUtil {
    private static PhoneUtil INSTANCE = null;

    public static PhoneUtil getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PhoneUtil();
        }
        return INSTANCE;
    }

    public static boolean isInternetAvailable(Context context) {
        NetworkInfo info = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info == null)
        {
            return false;
        }
        else
        {
            if(info.isConnected())
            {
                return true;
            }
            else
            {
                return true;
            }
        }
    }

    public void vibrating(Context context){
        Vibrator v = (Vibrator) context.getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v.vibrate(150);
        }
    }

    public void changeLanguage(Activity activity, String language){
        Locale myLocale = new Locale(language);
        Resources res = activity.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(activity, SplashActivity.class);
        activity.startActivity(refresh);
        activity.finish();
    }
}