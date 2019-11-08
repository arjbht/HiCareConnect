/*-----------------------------------------------------------------------------
 - Developed by Arjun Bhatt                                               -
 - Last modified 6/11/19 6:59 PM                                              -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.ab.hicarecommercialapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtility {

    public static final String IS_USER_LOGIN = "IS_USER_LOGIN";
    public static final String COMPANY_CODE = "COMPANY_CODE";
    public static final String COMPANY_NAME = "COMPANY_NAME";
    public static final String ACCOUNT_ID = "ACCOUNT_ID";
    public static final String IS_ACCOUNT_THERE = "IS_ACCOUNT_THERE";
    public static final String ACCOUNT_ADDRESS = "ACCOUNT_ADDRESS";
    public static final String IS_SELF_REGISTERED = "IS_SELF_REGISTERED";
    public static final String IS_GEOFENCE = "IS_GEOFENCE";
    public static final String IS_TODAY = "IS_TODAY";


    public static boolean getPrefBoolean(Context context, String key) {
        SharedPreferences prefs =
                context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        return prefs.getBoolean(key, false);
    }

    public static void savePrefBoolean(Context context, String key, boolean value) {
        SharedPreferences prefs =
                context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        prefs.edit().putBoolean(key, value).apply();
    }

    public static String getPrefString(Context context, String key) {
        SharedPreferences prefs =
                context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        return prefs.getString(key, "");
    }
    public static void savePrefString(Context context, String key, String value) {
        SharedPreferences prefs =
                context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        prefs.edit().putString(key, value).apply();
    }

}
