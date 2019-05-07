package com.android.tvnet.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.android.tvnet.activity.LoginActivity;
import com.android.tvnet.key.ConsKey;


public class SharedPrefsService {
    private static SharedPrefsService INSTANCE = null;

    public static SharedPrefsService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SharedPrefsService();
        }
        return INSTANCE;
    }



    public void saveUser(Context context, String accessToken) {

        SharedPreferences prefsService = context.getSharedPreferences(ConsKey.PREFS_NAME, 0);
        prefsService.edit().putString(ConsKey.USER_TOKEN, accessToken).apply();
    }

    public String getUserToken(Activity context) {
        SharedPreferences preferences = context.getSharedPreferences(ConsKey.PREFS_NAME, 0);
        String user =preferences.getString(ConsKey.USER_TOKEN, null);
        if (user == null) {
            saveUser(context, user);
            Intent intent = new Intent(context, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.getApplicationContext().startActivity(intent);
        }
        return user;

    }


}
