package com.herojeff.sulchedule.data;

import java.util.ArrayList;
import java.util.HashMap;

public class RecordDay {

    public int day;
    ArrayList<String> location_list = new ArrayList<>();
    ArrayList<String> friend_list = new ArrayList<>();
    HashMap<Integer, Integer> sul_list = new HashMap<>(); /*<sul_index, sul_count>*/
    boolean custom_expense_enabled = false;
    int custom_expense = 0;
    boolean custom_calorie_enabled = false;
    int custom_calorie = 0;
    boolean first_launch_of_day = true;

    public RecordDay(int day) {
        this.day = day;
    }

    ////
    public boolean isTodayEmpty() {
        if (custom_expense_enabled || custom_calorie_enabled || location_list.size() > 0 || friend_list.size() > 0) {
            return false;
        }
        if (sul_list.size() == 0) {
            return true;
        }
        for (int k : sul_list.keySet()) {
            if (sul_list.get(k) != 0) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<String> getFriend_list() {
        return friend_list;
    }

    public boolean containsDeletedSul(){
        for (int k : sul_list.keySet()) {
            if (sul_list.get(k) != 0 && !SharedResources.getSul(k).isSul_enabled()) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getLocation_list() {
        return location_list;
    }

    public HashMap<Integer, Integer> getSul_list() {
        return sul_list;
    }

    public void addFriend_list(String value) {
        friend_list.add(value);
    }

    public void addLocation_list(String value) {
        location_list.add(value);
    }

    public void setCertain_sul_count(int sul_index, int count) {
        if (sul_list.containsKey(sul_index)) {
            sul_list.remove(sul_index);
            sul_list.put(sul_index, count);
        } else {
            sul_list.put(sul_index, count);
        }
    }

    public void setCertain_sul_count(String sul_name, int count) {
        int sul_index = SharedResources.getSulIndex(sul_name);
        if (count < 0) {
            count = 0;
        }
        if (sul_list.containsKey(sul_index)) {
            sul_list.remove(sul_index);
            sul_list.put(sul_index, count);
        } else {
            sul_list.put(sul_index, count);
        }
    }

    public int getCertain_sul_count(int sul_index) {
        try {
            return sul_list.get(sul_index);
        } catch (Exception e) {
            return 0;
        }
    }

    public int getCertain_sul_count(String sul_name) {
        int sul_index = SharedResources.getSulIndex(sul_name);
        try {
            return sul_list.get(sul_index);
        } catch (Exception e) {
            return 0;
        }
    }

    public ArrayList<Sul> getDrunk_sul_arraylist() {
        ArrayList<Sul> return_sul = new ArrayList<>();
        for (Sul sul : SharedResources.getSuls()) {
            if (sul.isSul_enabled()) {
                return_sul.add(sul);
            }
        }
        return return_sul;
    }

    public boolean isCustom_dexpense_enabled() {
        return custom_expense_enabled;
    }

    public boolean isCustom_calorie_enabled() {
        return custom_calorie_enabled;
    }

    public void enableCustom_expense() {
        this.custom_expense_enabled = true;
    }

    public void disableCustom_expense() {
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

    public int getDay() {
        return day;
    }

    public int getCalorie() {
        if (custom_calorie_enabled) {
            return custom_calorie;
        } else {
            int sum = 0;
            for (Integer key : sul_list.keySet()) {
                sum += sul_list.get(key) * SharedResources.getSul(key).sul_calorie;
            }
            return sum;
        }
    }

    public int getExpense() {
        if (custom_expense_enabled) {
            return custom_expense;
        } else {
            int sum = 0;
            for (Integer key : sul_list.keySet()) {
                sum += sul_list.get(key) * SharedResources.getSul(key).sul_price;
            }
            return sum;
        }
    }
}
