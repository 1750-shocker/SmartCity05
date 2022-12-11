package com.example.smartcity05.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.smartcity05.MyApplication;

public class SPUtil {
    private static final String NAME = "sp";

    public static void putString(String key, String value) {
        SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences(NAME, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(String key, String defValue) {
        return MyApplication.getContext().getSharedPreferences(NAME, Context.MODE_PRIVATE).getString(key, defValue);
    }

    public static void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences(NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return MyApplication.getContext().getSharedPreferences(NAME, Context.MODE_PRIVATE).getBoolean(key, defValue);
    }

}
