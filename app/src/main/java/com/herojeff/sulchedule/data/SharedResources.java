package com.herojeff.sulchedule.data;

import android.graphics.Color;

import java.util.ArrayList;


public final class SharedResources {
    public static final int color_accent = Color.parseColor("#FFDC67");
    public static final int color_primary = Color.parseColor("#252B53");
    public static final int color_primary_dark = Color.parseColor("#0B102F");
    public static final int color_white = Color.parseColor("#FFFFFF");

    public static boolean enable_ad = true;
    public static boolean remove_ad_eligible = false;
    public static boolean first_launch_ever = true;

    public static ArrayList<RecordDay> recordDays = new ArrayList<>();
    public static ArrayList<RecordMonth> recordMonths = new ArrayList<>();
    public static ArrayList<Sul> suls = new ArrayList<>();

    public static boolean addSul(String sul_name, int sul_calorie, int sul_price, String sul_unit){
        if(!sulExists(sul_name)) {
            suls.add(new Sul(sul_name, sul_calorie, sul_price, sul_unit));
            return true;
        }
        else{
            return false;
        }
    }
    public static void removeSul(String sul_name){
        for(Sul sul:suls){
            if (sul.sul_name.equals(sul_name)){
                sul.disableSul();
            }
        }
    }
    public static void removeSul(int index){
        suls.get(index).disableSul();
    }
    public static Sul getSul(String sul_name){
        for(Sul sul:suls){
            if (sul.sul_name.equals(sul_name) && sul.isSul_enabled()){
                return sul;
            }
        }
        return null;
    }
    public static int getSulIndex(String sul_name){
        int index=0;
        for(Sul sul:suls){
            if (sul.sul_name.equals(sul_name) && sul.isSul_enabled()){
                return index;
            }
            index++;
        }
        return -1;
    }
    public static Sul getSul(int index){
        if(suls.get(index).isSul_enabled()) {
            return suls.get(index);
        }
        else{
            return null;
        }
    }
    public static boolean sulExists(String sul_name){
        for(Sul sul:suls){
            if (sul.sul_name.equals(sul_name) && sul.isSul_enabled()){
                return true;
            }
        }
        return false;
    }

    public static ArrayList<RecordDay> getMonthlyRecordDayArray(int year, int month){
        ArrayList<RecordDay> arr = new ArrayList<>();
        for(RecordDay recordDay:recordDays){
            if (recordDay.getYear() == year && recordDay.getMonth() == month) {
                arr.add(recordDay);
            }
        }
        return arr;
    }

    //the below 4 methods have a very bad filter design. revise.
    public static void appendRecordDay(RecordDay recordDay){
        if(!recordDay_exists(recordDay.year, recordDay.month, recordDay.day)) {
            recordDays.add(recordDay);
        }
    }
    public static void appendRecordDay(int year, int month, int day){
        if(!recordDay_exists(year, month, day)) {
            recordDays.add(new RecordDay(year, month, day));
        }
    }
    public static void appendRecordMonth(RecordMonth recordMonth){
        if(!recordMonth_exists(recordMonth.year, recordMonth.month)) {
            recordMonths.add(recordMonth);
        }
    }
    public static void appendRecordMonth(int year, int month){
        if(!recordMonth_exists(year, month)) {
            recordMonths.add(new RecordMonth(year, month));
        }
    }

    public static RecordDay getRecordDay(int year, int month, int day){
        for(RecordDay recordDay:recordDays){
            if(recordDay.year == year && recordDay.month == month && recordDay.day == day){
                return recordDay;
            }
        }
        return new RecordDay(year, month, day);
    }
    public static RecordDay getRecordDay(int index){
        return recordDays.get(index);
    }
    public static boolean recordDay_exists(int year, int month, int day){
        for(RecordDay recordDay:recordDays){
            if(recordDay.year == year && recordDay.month == month && recordDay.day == day){
                return true;
            }
        }
        return false;
    }
    public static RecordMonth getRecordMonth(int year, int month){
        for(RecordMonth recordMonth:recordMonths){
            if(recordMonth.year == year && recordMonth.month == month){
                return recordMonth;
            }
        }
        return new RecordMonth(year, month);
    }
    public static boolean recordMonth_exists(int year, int month){
        for(RecordMonth recordMonth:recordMonths){
            if(recordMonth.year == year && recordMonth.month == month){
                return true;
            }
        }
        return false;
    }


    public static void save(){

    }
    public static void load(){

    }
}
