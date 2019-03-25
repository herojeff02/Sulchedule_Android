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
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        //smart tip on?
        String json = gson.toJson(SharedResources.enable_smart_tip_string);
        prefsEditor.putString("smartTipEnabled", json);
        //first launch?
        json = gson.toJson(SharedResources.first_launch_ever);
        prefsEditor.putString("firstLaunchEver", json);
        //ad on?
        json = gson.toJson(SharedResources.enable_ad);
        prefsEditor.putString("adEnabled", json);
        //eligible for ad off?
        json = gson.toJson(SharedResources.remove_ad_eligible);
        prefsEditor.putString("eligibleForAdDisable", json);

        //commit
        prefsEditor.commit();
    }

    public static void save(){
        saveSulArrayList();
        saveRecordArrayList();
        saveUserSettings();
    }

    public static void load() {
        Gson gson = new Gson();

        String json = mPrefs.getString("suls", "");
        SharedResources.setSulsRAW(gson.fromJson(json, new TypeToken<ArrayList<Sul>>(){}.getType()));

        json = mPrefs.getString("recordMonths", "");
        SharedResources.setRecordMonthsRAW(gson.fromJson(json, new TypeToken<ArrayList<RecordMonth>>(){}.getType()));

        json = mPrefs.getString("smartTipEnabled", "");
        SharedResources.enable_smart_tip_string = gson.fromJson(json, Boolean.class);
        json = mPrefs.getString("adEnabled", "");
        SharedResources.enable_ad = gson.fromJson(json, Boolean.class);
        json = mPrefs.getString("eligibleForAdDisable", "");
        SharedResources.remove_ad_eligible = gson.fromJson(json, Boolean.class);
    }

    public static boolean getFirstLaunch() {
        Gson gson = new Gson();
        String json = mPrefs.getString("firstLaunchEver", "");
        try {
            SharedResources.first_launch_ever = gson.fromJson(json, Boolean.class);
        } catch (Exception e){
            SharedResources.first_launch_ever = true;
            return true;
        }

        return SharedResources.first_launch_ever;
    }
}
