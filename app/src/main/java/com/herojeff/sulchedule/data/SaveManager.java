package com.herojeff.sulchedule.data;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public final class SaveManager {
    static SharedPreferences mPrefs;

    public static void setPrefs(SharedPreferences prefs){//must be run at onCreate
        mPrefs = prefs;
    }

    public static void saveSulArrayList() {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(SharedResources.getSulsRAW());
        prefsEditor.putString("suls", json);
        prefsEditor.commit();
    }
    public static void saveRecordArrayList(){
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(SharedResources.getRecordMonthsRAW());
        prefsEditor.putString("recordMonths", json);
        prefsEditor.commit();
    }
    public static void saveUserSettings(){
        //smart tip on?
        //first launch?
        //ad on?
        //eligible for ad off?
    }
    public static void saveGoal(){

    }

    public static void save(){
        saveSulArrayList();
        saveRecordArrayList();
        saveUserSettings();
        saveGoal();
    }

    public static void load() {
        Gson gson = new Gson();

        String json = mPrefs.getString("suls", "");
        SharedResources.setSulsRAW(gson.fromJson(json, new TypeToken<ArrayList<Sul>>(){}.getType()));

        json = mPrefs.getString("recordMonths", "");
        SharedResources.setRecordMonthsRAW(gson.fromJson(json, new TypeToken<ArrayList<Sul>>(){}.getType()));

//        json = mPrefs.getString("suls", "");
//        SharedResources.setSulsRAW(gson.fromJson(json, new TypeToken<ArrayList<Sul>>(){}.getType()));
//
//        json = mPrefs.getString("suls", "");
//        SharedResources.setSulsRAW(gson.fromJson(json, new TypeToken<ArrayList<Sul>>(){}.getType()));
    }
}
