package com.herojeff.sulchedule.data;

import android.content.SharedPreferences;

import com.google.gson.Gson;

public final class SaveManager {
    private static final Object MyObject = new MyObject();
    public static SharedPreferences mPrefs;

    public static void setPrefs(SharedPreferences tempPrefs) {
        mPrefs = tempPrefs;
    }

    public static void save() {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(MyObject);
        prefsEditor.putString("MyObject", json);
        prefsEditor.commit();
    }

    public static void load(SharedPreferences mPrefs) {
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        MyObject obj = gson.fromJson(json, MyObject.class);
    }

    static class MyObject {
        int k = 2352;

        public MyObject() {
        }
    }
}
