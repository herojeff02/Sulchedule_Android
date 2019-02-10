package com.herojeff.sulchedule.data;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public final class SharedResources {
    public static final int color_accent = Color.parseColor("#FFDC67");
    public static final int color_primary = Color.parseColor("#252B53");
    public static final int color_primary_dark = Color.parseColor("#0B102F");
    public static final int color_white = Color.parseColor("#FFFFFF");
    public static final int color_primary_dark_dark = Color.parseColor("#090E1D");


    public static final int color_traffic_green = Color.parseColor("#4AD863");
    public static final int color_traffic_yellow = Color.parseColor("#FFC400");
    public static final int color_traffic_red = Color.parseColor("#FF6060");

    public static boolean enable_ad = true;
    public static boolean remove_ad_eligible = false;
    public static boolean first_launch_ever = true;

    public static ArrayList<RecordMonth> recordMonths = new ArrayList<>();
    private static ArrayList<Sul> suls = new ArrayList<>();

    public static int getYear(){
        Date today = new Date();
        return today.getYear();
    }
    public static int getMonth(){
        Date today = new Date();
        return today.getMonth() + 1;
    }
    public static int getDay(){
        Date today = new Date();
        return today.getDate();
    }
    public static String getWeekDayKorean(){
        Date today = new Date();
        String[] weekDayKorean = {"일", "월", "화", "수", "목", "금", "토", "일"};
        return weekDayKorean[today.getDay()];
    }



    //sul
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
        if(index == -1){
            return null;
        }
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


    public static ArrayList<Sul> getFavouriteSuls(){
        ArrayList<Sul> favourites = new ArrayList<>();
        for(Sul sul:suls){
            if(sul.isFavourite() && sul.isSul_enabled()){
                favourites.add(sul);
            }
        }
        return favourites;
    }

    public static ArrayList<Sul> getSuls(){
        ArrayList<Sul> suls = new ArrayList<>();
        for(Sul sul: SharedResources.suls){
            if(sul.isSul_enabled()){
                suls.add(sul);
            }
        }
        return suls;
    }

    public static ArrayList<Sul> getMainSuls(int year, int month, int day){
        ArrayList<Sul> suls = getFavouriteSuls();

        RecordDay recordDay = getRecordDay(year, month, day);
        for(int sul_index: recordDay.sul_list.keySet()){
            if(recordDay.getCertain_sul_count(sul_index) != 0 && !getSul(sul_index).isFavourite()){
                suls.add(getSul(sul_index));
            }
        }
        return suls;
    }

    //records
    public static ArrayList<RecordDay> getMonthlyRecordDayArray(int year, int month){
        for(RecordMonth recordMonth:recordMonths){
            if (recordMonth.year == year && recordMonth.month == month) {
                return recordMonth.getRecordDays();
            }
        }
        return appendRecordMonth(year, month).getRecordDays();
    }

    public static void appendRecordDay(int year, int month, @org.jetbrains.annotations.NotNull RecordDay recordDay){
        if(!recordDay_exists(year, month, recordDay.day)) {
            getRecordMonth(year, month).getRecordDays().add(recordDay);
        }
    }
    public static RecordDay appendRecordDay(int year, int month, int day){
        if(!recordDay_exists(year, month, day)) {
            RecordDay recordDay = new RecordDay(day);
            getRecordMonth(year, month).getRecordDays().add(recordDay);
            return recordDay;
        }
        else {
            return getRecordDay(year, month, day);
        }
    }
    public static RecordMonth appendRecordMonth(RecordMonth recordMonth){
        if(!recordMonth_exists(recordMonth.year, recordMonth.month)) {
            recordMonths.add(recordMonth);
            return recordMonth;
        }
        else{
            return getRecordMonth(recordMonth.year, recordMonth.month);
        }
    }
    public static RecordMonth appendRecordMonth(int year, int month){
        if(!recordMonth_exists(year, month)) {
            RecordMonth recordMonth = new RecordMonth(year, month);
            recordMonths.add(recordMonth);
            return recordMonth;
        }
        else{
            return getRecordMonth(year, month);
        }
    }

    public static RecordDay getRecordDay(int year, int month, int day){
        for(RecordMonth recordMonth:recordMonths){
            if(recordMonth.year == year && recordMonth.month == month){
                for(RecordDay recordDay:recordMonth.getRecordDays()){
                    if(recordDay.day == day){
                        return recordDay;
                    }
                }
            }
        }
        return appendRecordDay(year, month, day);
    }
    public static boolean recordDay_exists(int year, int month, int day){
        for(RecordMonth recordMonth:recordMonths){
            if(recordMonth.year == year && recordMonth.month == month){
                for(RecordDay recordDay:recordMonth.getRecordDays()){
                    if(recordDay.day == day){
                        return true;
                    }
                }
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
        return appendRecordMonth(year, month);
    }
    public static RecordMonth getRecordMonth(){
        int year = getYear();
        int month = getMonth();
        for(RecordMonth recordMonth:recordMonths){
            if(recordMonth.year == year && recordMonth.month == month){
                return recordMonth;
            }
        }
        return appendRecordMonth(year, month);
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

    public static void setFavouriteSul(String sul_name, boolean set) {
        getSul(sul_name).setFavourite(set);
    }
}
