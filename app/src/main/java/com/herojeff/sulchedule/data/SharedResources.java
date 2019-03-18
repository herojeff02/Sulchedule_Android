package com.herojeff.sulchedule.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;


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

    public static boolean smart_tip_string = true;

    static String[] helloList = {"안녕하세요!", "어떤 일로 오셨나요?(불안)", "건강한 음주 되세요!", "전 당신을 믿습니다.", "환영합니다!"};
    static int randomIndex = new Random().nextInt(helloList.length);

    public static ArrayList<RecordMonth> recordMonths = new ArrayList<>();
    private static ArrayList<Sul> suls = new ArrayList<>();

    public static void setSuls(ArrayList<Sul> sul){
        suls = sul;
    }

    public static int getYear() {
        Date today = new Date();
        return today.getYear();
    }

    public static int getMonth() {
        Date today = new Date();
        return today.getMonth() + 1;
    }

    public static int getDay() {
        Date today = new Date();
        return today.getDate();
    }

    public static String getWeekDayKorean() {
        Date today = new Date();
        String[] weekDayKorean = {"일", "월", "화", "수", "목", "금", "토", "일"};
        return weekDayKorean[today.getDay()];
    }


    //sul
    public static boolean addSul(String sul_name, int sul_calorie, int sul_price, String sul_unit) {
        if (!sulExists(sul_name)) {
            suls.add(new Sul(sul_name, sul_calorie, sul_price, sul_unit));
            return true;
        } else {
            return false;
        }
    }

    public static void removeSul(String sul_name) {
        for (Sul sul : suls) {
            if (sul.sul_name.equals(sul_name)) {
                sul.disableSul();
            }
        }
    }

    public static void removeSul(int index) {
        suls.get(index).disableSul();
    }

    public static Sul getSul(String sul_name) {
        for (Sul sul : suls) {
            if (sul.sul_name.equals(sul_name) && sul.isSul_enabled()) {
                return sul;
            }
        }
        return null;
    }

    public static int getSulIndex(String sul_name) {
        int index = 0;
        for (Sul sul : suls) {
            if (sul.sul_name.equals(sul_name) && sul.isSul_enabled()) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public static Sul getSul(int index) {
        if (index == -1) {
            return null;
        }
        if (suls.get(index).isSul_enabled()) {
            return suls.get(index);
        } else {
            return null;
        }
    }

    public static boolean sulExists(String sul_name) {
        for (Sul sul : suls) {
            if (sul.sul_name.equals(sul_name) && sul.isSul_enabled()) {
                return true;
            }
        }
        return false;
    }


    public static ArrayList<Sul> getFavouriteSuls() {
        ArrayList<Sul> favourites = new ArrayList<>();
        for (Sul sul : suls) {
            if (sul.isFavourite() && sul.isSul_enabled()) {
                favourites.add(sul);
            }
        }
        return favourites;
    }

    public static ArrayList<Sul> getSuls() {
        ArrayList<Sul> suls = new ArrayList<>();
        for (Sul sul : SharedResources.suls) {
            if (sul.isSul_enabled()) {
                suls.add(sul);
            }
        }
        return suls;
    }

    public static ArrayList<Sul> getMainSuls(int year, int month, int day) {
        ArrayList<Sul> suls = getFavouriteSuls();

        RecordDay recordDay = getRecordDay(year, month, day);
        for (int sul_index : recordDay.sul_list.keySet()) {
            if (recordDay.getCertain_sul_count(sul_index) != 0 && !getSul(sul_index).isFavourite()) {
                suls.add(getSul(sul_index));
            }
        }
        return suls;
    }

    //records
    public static ArrayList<RecordDay> getRecentRecordDays(int year, int month, int day) {
        ArrayList<RecordDay> returnArray = new ArrayList<>();
        if(day < 14){
            returnArray.addAll(SharedResources.getMonthlyRecordDayArrayFromLastMonth(year, month, SharedResources.getDay()));
            returnArray.addAll(SharedResources.getMonthlyRecordDayArray(year, month));
        }
        else{
            returnArray.addAll(SharedResources.getMonthlyRecordDayArrayStartingFrom(year, month, day));
        }
        return returnArray;
    }
    //records
    public static ArrayList<RecordDay> getMonthlyRecordDayArrayStartingFrom(int year, int month, int day) {
        ArrayList<RecordDay> returnArray = new ArrayList<>();
        for (RecordMonth recordMonth : recordMonths) {
            if (recordMonth.year == year && recordMonth.month == month) {
                for(RecordDay recordDay : recordMonth.getRecordDays()) {
                    if(recordDay.getDay() > day-14) {
                        returnArray.add(recordDay);
                    }
                }
            }
        }
        return returnArray;
    }
    //records
    public static ArrayList<RecordDay> getMonthlyRecordDayArrayFromLastMonth(int year, int month, int day) {
        month--;
        int[] lastDayOfMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
        ArrayList<RecordDay> returnArray = new ArrayList<>();
        for (RecordMonth recordMonth : recordMonths) {
            if (recordMonth.year == year && recordMonth.month == month) {
                for(RecordDay recordDay : recordMonth.getRecordDays()) {
                    int finalDayOfMonth = lastDayOfMonth[month-1];
                    if(recordDay.getDay() > finalDayOfMonth-14+day) {
                        returnArray.add(recordDay);
                    }
                }
            }
        }
        return returnArray;
    }
    //records
    public static ArrayList<RecordDay> getMonthlyRecordDayArray(int year, int month) {
        for (RecordMonth recordMonth : recordMonths) {
            if (recordMonth.year == year && recordMonth.month == month) {
                return recordMonth.getRecordDays();
            }
        }
        return appendRecordMonth(year, month).getRecordDays();
    }

    public static void appendRecordDay(int year, int month, @org.jetbrains.annotations.NotNull RecordDay recordDay) {
        if (!recordDay_exists(year, month, recordDay.day)) {
            getRecordMonth(year, month).getRecordDays().add(recordDay);
        }
    }

    public static RecordDay appendRecordDay(int year, int month, int day) {
        if (!recordDay_exists(year, month, day)) {
            RecordDay recordDay = new RecordDay(day);
            getRecordMonth(year, month).getRecordDays().add(recordDay);
            return recordDay;
        } else {
            return getRecordDay(year, month, day);
        }
    }

    public static RecordMonth appendRecordMonth(RecordMonth recordMonth) {
        if (!recordMonth_exists(recordMonth.year, recordMonth.month)) {
            recordMonths.add(recordMonth);
            return recordMonth;
        } else {
            return getRecordMonth(recordMonth.year, recordMonth.month);
        }
    }

    public static RecordMonth appendRecordMonth(int year, int month) {
        if (!recordMonth_exists(year, month)) {
            RecordMonth recordMonth = new RecordMonth(year, month);
            recordMonths.add(recordMonth);
            return recordMonth;
        } else {
            return getRecordMonth(year, month);
        }
    }

    public static RecordDay getRecordDay(int year, int month, int day) {
        for (RecordMonth recordMonth : recordMonths) {
            if (recordMonth.year == year && recordMonth.month == month) {
                for (RecordDay recordDay : recordMonth.getRecordDays()) {
                    if (recordDay.day == day) {
                        return recordDay;
                    }
                }
            }
        }
        return appendRecordDay(year, month, day);
    }

    public static boolean recordDay_exists(int year, int month, int day) {
        for (RecordMonth recordMonth : recordMonths) {
            if (recordMonth.year == year && recordMonth.month == month) {
                for (RecordDay recordDay : recordMonth.getRecordDays()) {
                    if (recordDay.day == day) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static RecordMonth getRecordMonth(int year, int month) {
        for (RecordMonth recordMonth : recordMonths) {
            if (recordMonth.year == year && recordMonth.month == month) {
                return recordMonth;
            }
        }
        return appendRecordMonth(year, month);
    }

    public static RecordMonth getRecordMonth() {
        int year = getYear();
        int month = getMonth();
        for (RecordMonth recordMonth : recordMonths) {
            if (recordMonth.year == year && recordMonth.month == month) {
                return recordMonth;
            }
        }
        return appendRecordMonth(year, month);
    }

    public static boolean recordMonth_exists(int year, int month) {
        for (RecordMonth recordMonth : recordMonths) {
            if (recordMonth.year == year && recordMonth.month == month) {
                return true;
            }
        }
        return false;
    }




    public static void setFavouriteSul(String sul_name, boolean set) {
        getSul(sul_name).setFavourite(set);
    }

    public static String getSmartTipString(int year, int month, int day, boolean refresh_default){
        RecordMonth recordMonth = getRecordMonth(year, month);
        RecordDay recordDay = getRecordDay(year, month, day);
        String returnString;

        if(smart_tip_string) {
            int sul_count = 0;

            for (int key: recordDay.getSul_list().keySet()) {
                if(recordDay.getSul_list().get(key)!=0){
                    sul_count++;
                }
            }

            ArrayList<SmartTipPriorityPair> priorities = new ArrayList<>(Arrays.asList(new SmartTipPriorityPair(recordDay.getExpense(), Mode.DayEXPENSE)
                    , new SmartTipPriorityPair(recordDay.getCalorie(), Mode.DayCALORIE)
                    , new SmartTipPriorityPair(sul_count, Mode.DaySULKIND)
                    , new SmartTipPriorityPair(recordDay.getFriend_list().size(), Mode.DayFRIENDCOUNT)
                    , new SmartTipPriorityPair(recordDay.getLocation_list().size(), Mode.DayLOCATIONLIST)
                    , new SmartTipPriorityPair(recordMonth.stat_streakOfMonth(), Mode.MonthSTREAK)
                    , new SmartTipPriorityPair(recordMonth.stat_daysOfMonth(), Mode.MonthCOUNT)
                    , new SmartTipPriorityPair(recordMonth.stat_totalExpense(), Mode.MonthEXPENSE)
                    , new SmartTipPriorityPair(recordMonth.stat_caloriesOfMonth(), Mode.MonthCALORIE)));

            Collections.sort(priorities, new DescendingSmartTipPriorityPairValue());

            if (recordDay.isTodayEmpty()) {
                if(refresh_default){
                    randomIndex = new Random().nextInt(helloList.length);
                }
                returnString = helloList[randomIndex];
            } else {
                returnString = priorities.get(0).getSmartTipString();
            }
        }
        else{
            returnString = recordDay.getCalorie() + "kcal, " + recordDay.getExpense() + "원";
        }

        return returnString;
    }

    static class SmartTipPriorityPair{
        int value;
        int comparison;
        Mode mode;
        double priority;
        boolean day_check = false;

        public SmartTipPriorityPair(int value, int comparison, Mode mode) {
            this.value = value;
            this.comparison = comparison;
            this.mode = mode;
        }
        public SmartTipPriorityPair(int value, Mode mode){
            this.value = value;
            this.mode = mode;
            priority = getPriority();
        }

        public double getPriority(){
            switch (mode){
                case DayEXPENSE:
                    priority = (double)value / (double)20000;
                    break;
                case DayCALORIE:
                    priority = (double)value / (double)1000;
                    break;
                case DayFRIENDCOUNT:
                    if(value>5){
                        priority = 6.0;
                    }
                    else {
                        priority = 0.0;
                    }
                case DayLOCATIONLIST:
                    if(value>3){
                        priority = 8.0;
                    }
                    else {
                        priority = 0.0;
                    }
                    break;
                case DaySULKIND:
                    if(value>4){
                        priority = 10.0;
                    }
                    else {
                        priority = 0.0;
                    }
                    break;
                case MonthCALORIE:
                    priority = (double)value / (double)6000;
                    break;
                case MonthCOUNT:
                    priority = (double)value / (double)8;
                    break;
                case MonthEXPENSE:
                    priority = (double)value / (double)100000;
                    break;
                case MonthSTREAK:
                    priority = (double)value / (double)4;
                    break;
            }
            return priority;
        }

        public String getSmartTipString(){
            switch (mode){
                case DayEXPENSE:
                    return "오늘 "+value+"원을 쓰셨어요!";
                case DayCALORIE:
                    return String.format("%.1f", getKM(value)) +"km를 걸으셔야 체중이 유지됩니다.";
                case DayFRIENDCOUNT:
                    return "친구가 꽤 많으시네요!";
                case DayLOCATIONLIST:
                    return "몇 차까지 가실 건가요?";
                case DaySULKIND:
                    return "술 종류가 이렇게 많은지 몰랐어요.";
                case MonthCALORIE:
                    return "이번 달에 "+value+ "kcal를 쓰셔야 해요!";
                case MonthCOUNT:
                    return "이번 달에 " + value +"일 음주하셨어요!";
                case MonthEXPENSE:
                    return "이번 달에 "+value+"원을 쓰셨어요!";
                case MonthSTREAK:
                    return "연이은 음주는 건강에 나빠요 ㅠㅠ";
                default:
                    return "";
            }
        }
        public double getKM(int value){
            return (double)value/50.0;
        }
    }

    enum Mode {
        DayEXPENSE, DayCALORIE, DaySULKIND, DayFRIENDCOUNT, DayLOCATIONLIST, MonthSTREAK, MonthCOUNT, MonthEXPENSE, MonthCALORIE
    }

    static class DescendingSmartTipPriorityPairValue implements Comparator<SmartTipPriorityPair> {
        @Override
        public int compare(SmartTipPriorityPair a, SmartTipPriorityPair b) {
            Double tmp = b.priority;
            return tmp.compareTo(a.priority);
        }
    }
}
