package com.herojeff.sulchedule.data;

import java.util.Calendar;
import java.util.Date;

public class CustomDayManager {
    private static int year;
    private static int month;
    private static int day;
    private static int[] lastDayOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static void initCustomDay() {
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

    public static int getYear() {
        return year;
    }

    public static int getMonth() {
        return month;
    }

    public static int getDay() {
        return day;
    }

    public static String getWeekDayKorean(int year, int month, int day) {
        Date today = new Date(year, month - 1, day - 1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);

        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                return "월";
            case 2:
                return "화";
            case 3:
                return "수";
            case 4:
                return "목";
            case 5:
                return "금";
            case 6:
                return "토";
            case 7:
                return "일";
        }

        return "WTF";

//        String[] weekDayKorean = {월", "화", "수", "목", "금", "토", "일"};
//        return weekDayKorean[today.getDay()];
    }

    public static int getLastDayOfMonth(int month) {
        int[] lastDayOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return lastDayOfMonth[month - 1];
    }

    public static void setNextDay() {
        if (lastDayOfMonth[month - 1] < day + 1) {
            day = 1;
            setNextMonth();
        } else {
            day++;
        }
    }

    public static void setNextMonth() {
        if (month + 1 > 12) {
            year++;
            month = 1;
        } else {
            month++;
        }
    }

    public static void setPrevDay() {
        if (1 > day - 1) {
            setPrevMonth();
            day = lastDayOfMonth[month - 1];
        } else {
            day--;
        }
    }

    public static void setPrevMonth() {
        if (month - 1 < 1) {
            year--;
            month = 12;
        } else {
            month--;
        }
    }
}
