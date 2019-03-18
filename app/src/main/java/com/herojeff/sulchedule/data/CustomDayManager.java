package com.herojeff.sulchedule.data;

import java.util.Date;

public class CustomDayManager {
    private static int year;
    private static int month;
    private static int day;
    int[] lastDayOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static void initCustomDay(){
        year = getTodayYear();
        month = getTodayMonth();
        day = getTodayDay();
    }

    public static int getTodayYear() {
        Date today = new Date();
        return today.getYear();
    }

    public static int getTodayMonth() {
        Date today = new Date();
        return today.getMonth() + 1;
    }

    public static int getTodayDay() {
        Date today = new Date();
        return today.getDate();
    }

    public static String getTodayWeekDayKorean() {
        Date today = new Date();
        String[] weekDayKorean = {"일", "월", "화", "수", "목", "금", "토", "일"};
        return weekDayKorean[today.getDay()];
    }

    public static int getYear() {
        return year;
    }

    public static int getMonth() {
        return month;
    }

    public static int getDay() {
        return day;
    }

    public static String getWeekDayKorean() {
        Date today = new Date(year, month, day);
        String[] weekDayKorean = {"일", "월", "화", "수", "목", "금", "토", "일"};
        return weekDayKorean[today.getDay()];
    }

    public void setNextDay() {
        if (lastDayOfMonth[month - 1] < day + 1) {
            day = 1;
            setNextMonth();
        } else {
            day++;
        }
    }

    public void setNextMonth() {
        if (month + 1 > 12) {
            year++;
            month = 1;
        } else {
            month++;
        }
    }

    public void setPrevDay() {
        if (1 > day - 1) {
            setPrevMonth();
            day = lastDayOfMonth[month - 1];
        } else {
            day--;
        }
    }

    public void setPrevMonth() {
        if (month - 1 < 1) {
            year--;
            month = 12;
        } else {
            month--;
        }
    }

    public static int getLastDayOfMonth(int month){
        int[] lastDayOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return lastDayOfMonth[month-1];
    }
}
