package com.herojeff.sulchedule.data;

import java.util.ArrayList;
import java.util.HashMap;

public class RecordDay {

    public RecordDay(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int year;
    public int month;
    public int day;

    ArrayList<String> location_list;
    ArrayList<String> friend_list;
    HashMap<Integer, Integer> sul_list; /*<sul_index, sul_count>*/

    boolean custom_expense_enabled = false;
    int custom_expense = 0;

    boolean custom_calorie_enabled = false;
    int custom_calorie = 0;

    boolean first_launch_of_day = true;

    ////

    public ArrayList<String> getFriend_list() {
        return friend_list;
    }
    public ArrayList<String> getLocation_list() {
        return location_list;
    }
    public HashMap<Integer, Integer> getSul_list() {
        return sul_list;
    }
    public void addFriend_list(String value){
        friend_list.add(value);
    }
    public void addLocation_list(String value){
        location_list.add(value);
    }
    public void setCertain_sul_count(int sul_index, int count){
        if(sul_list.containsKey(sul_index)){
            sul_list.remove(sul_index);
            sul_list.put(sul_index, count);
        }
        else{
            sul_list.put(sul_index, count);
        }
    }

    public boolean isCustom_dexpense_enabled() {
        return custom_expense_enabled;
    }
    public boolean isCustom_calorie_enabled() {
        return custom_calorie_enabled;
    }

    public void enableCustom_expense(){
        this.custom_expense_enabled = true;
    }
    public void disableCustom_expense(){
        this.custom_calorie_enabled = false;
    }

    public void enabledCustom_calorie_enabled() {
        this.custom_calorie_enabled = true;
    }
    public void disableCustom_calorie_enabled() {
        this.custom_calorie_enabled = false;
    }

    public void setCustom_expense(int custom_expense) {
        custom_expense_enabled = true;
        this.custom_expense = custom_expense;
    }
    public void setCustom_calorie(int custom_calorie) {
        custom_calorie_enabled = true;
        this.custom_calorie = custom_calorie;
    }

    public boolean isFirst_launch_of_day() {
        return first_launch_of_day;
    }

    public void disableFirst_launch_of_day() {
        this.first_launch_of_day = false;
    }

    public int getYear(){
        return year;
    }
    public int getMonth(){
        return month;
    }
    public int getDay(){
        return day;
    }

    public int getCalorie(){
        if(custom_calorie_enabled){
            return custom_calorie;
        }
        else{
            /**add calorie calculation here;**/
            return 1000;
        }
    }
    public int getExpense(){
        if(custom_expense_enabled){
            return custom_expense;
        }
        else{
            /**add expense calculation here;**/
            return 1000;
        }
    }
}
