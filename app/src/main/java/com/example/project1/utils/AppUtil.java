package com.example.project1.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class AppUtil {

    ///////////////////////////////////////////////////////////////////////////
    // Start activity
    ///////////////////////////////////////////////////////////////////////////
    public static void startActivity(Context act, Class<?> clz) {
        Intent intent = new Intent(act, clz);
        act.startActivity(intent);
    }

    public static void startActivity(Context act, Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(act, clz);
        intent.putExtras(bundle);
        act.startActivity(intent);
    }

    public static void startActivity(Context act, Class<?> clz, int key) {
        Intent intent = new Intent(act, clz);
        intent.putExtra("key", key);
        act.startActivity(intent);
    }

    public static void startActivity(Context act, Class<?> clz, String key, String value) {
        Intent intent = new Intent(act, clz);
        intent.putExtra(key, value);
        act.startActivity(intent);
    }
}
