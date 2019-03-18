package com.herojeff.sulchedule.data;

import java.util.Date;

public class CustomDayManager {
    int[] lastDayOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int year;
    private int month;
    private int day;

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
        Date today = new Date();
        return today.getYear();
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static int getMonth() {
        Date today = new Date();
        return today.getMonth() + 1;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public static int getDay() {
        Date today = new Date();
        return today.getDate();
    }

    public void setDay(int day) {
        this.day = day;
    }

    public static String getWeekDayKorean() {
        Date today = new Date();
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
}
