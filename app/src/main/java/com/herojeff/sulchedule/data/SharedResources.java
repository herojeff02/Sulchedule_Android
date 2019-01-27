package com.herojeff.sulchedule.data;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public final class SharedResources {
    public static final int color_accent = Color.parseColor("#FFDC67");
    public static final int color_primary = Color.parseColor("#252B53");
    public static final int color_primary_dark = Color.parseColor("#0B102F");
    public static final int color_white = Color.parseColor("#FFFFFF");

    public boolean enable_ad = true;
    public boolean remove_ad_eligible = false;
    public boolean first_launch_ever = true;

    public ArrayList<RecordDay> recordDays = new ArrayList<>();
    public ArrayList<RecordMonth> recordMonths = new ArrayList<>();
    public ArrayList<Sul> suls = new ArrayList<>();

    public SharedResources(){
        //test sul
        suls.add(new Sul("소주", 300, 4000, "병"));
        suls.add(new Sul("소주", 50, 650, "잔"));
        suls.add(new Sul("병맥주 330ml", 122, 2000, "병"));
        suls.add(new Sul("병맥주 500ml", 185, 3000, "병"));
        suls.add(new Sul("생맥주 500cc", 185, 4000, "잔"));
        suls.add(new Sul("캔맥주 355ml", 152, 2000, "캔"));
        suls.add(new Sul("레드와인", 84, 12000, "잔"));
        suls.add(new Sul("화이트와인", 74, 12000, "잔"));
        suls.add(new Sul("막걸리", 345, 2000, "병"));
    }

    public void addSul(String sul_name, int sul_calorie, int sul_price, String sul_unit){
        suls.add(new Sul(sul_name, sul_calorie, sul_price, sul_unit));
    }
    public void removeSul(String sul_name){
        for(Sul sul:suls){
            if (sul.sul_name.equals(sul_name)){
                sul.disableSul();
            }
        }
    }
    public void removeSul(int index){
        suls.get(index).disableSul();
    }
    public Sul getSul(String sul_name){
        for(Sul sul:suls){
            if (sul.sul_name.equals(sul_name) && sul.isSul_enabled()){
                return sul;
            }
        }
        return null;
    }
    public Sul getSul(int index){
        if(suls.get(index).isSul_enabled()) {
            return suls.get(index);
        }
        else{
            return null;
        }
    }

    //the below 4 methods have a very bad filter design. revise.
    public void appendRecordDay(RecordDay recordDay){
        if(!recordDay_exists(recordDay.year, recordDay.month, recordDay.day)) {
            recordDays.add(recordDay);
        }
    }
    public void appendRecordDay(int year, int month, int day){
        if(!recordDay_exists(year, month, day)) {
            recordDays.add(new RecordDay(year, month, day));
        }
    }
    public void appendRecordMonth(RecordMonth recordMonth){
        if(!recordMonth_exists(recordMonth.year, recordMonth.month)) {
            recordMonths.add(recordMonth);
        }
    }
    public void appendRecordMonth(int year, int month){
        if(!recordMonth_exists(year, month)) {
            recordMonths.add(new RecordMonth(year, month));
        }
    }

    public RecordDay getRecordDay(int year, int month, int day){
        for(RecordDay recordDay:recordDays){
            if(recordDay.year == year && recordDay.month == month && recordDay.day == day){
                return recordDay;
            }
        }
        return new RecordDay(year, month, day);
    }
    public boolean recordDay_exists(int year, int month, int day){
        for(RecordDay recordDay:recordDays){
            if(recordDay.year == year && recordDay.month == month && recordDay.day == day){
                return true;
            }
        }
        return false;
    }
    public RecordMonth getRecordMonth(int year, int month){
        for(RecordMonth recordMonth:recordMonths){
            if(recordMonth.year == year && recordMonth.month == month){
                return recordMonth;
            }
        }
        return new RecordMonth(year, month);
    }
    public boolean recordMonth_exists(int year, int month){
        for(RecordMonth recordMonth:recordMonths){
            if(recordMonth.year == year && recordMonth.month == month){
                return true;
            }
        }
        return false;
    }


    public void save(){

    }
    public void load(){

    }
}
