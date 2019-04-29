package com.herojeff.sulchedule.data;

import java.util.ArrayList;
import java.util.HashMap;

public class RecordDay {

    private int day;

    private ArrayList<ModeValuePair> moreInfo_list = new ArrayList<>();
    private HashMap<Integer, Integer> sul_list = new HashMap<>(); /*<sul_index, sul_count>*/
    private boolean custom_calorie_enabled = false;
    private int custom_calorie = 0;
    private boolean first_launch_of_day = true;

    public RecordDay(int day) {
        this.day = day;
    }

    ////
    public boolean isTodayEmpty() {
        if (custom_calorie_enabled || moreInfo_list.size() > 0) {
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

    public boolean containsDeletedSul() {
        for (int k : sul_list.keySet()) {
            if (sul_list.get(k) != 0 && !SharedResources.getSul(k).isSul_enabled()) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<ModeValuePair> getMoreInfo_list() {
        return moreInfo_list;
    }

    public void addMoreInfo_list(ModeValuePair info) {
        this.moreInfo_list.add(info);
    }

    public void addMoreInfo_list(int mode, String value) {
        this.moreInfo_list.add(new ModeValuePair(mode, value));
    }

    public HashMap<Integer, Integer> getSul_list() {
        return sul_list;
    }

    public void setCertain_sul_count(int sul_index, int count) {
        if (sul_list.containsKey(sul_index)) {
            sul_list.remove(sul_index);
            sul_list.put(sul_index, count);
        } else {
            sul_list.put(sul_index, count);
        }
    }

    public boolean hasCustomExpense() {
        for (ModeValuePair e : moreInfo_list) {
            if (e.getMode() == 2) {
                return true;
            }
        }
        return false;
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

    public boolean isCustom_calorie_enabled() {
        return custom_calorie_enabled;
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
        if (hasCustomExpense()) {
            return getCustomExpense();
        } else {
            int sum = 0;
            for (Integer key : sul_list.keySet()) {
                sum += sul_list.get(key) * SharedResources.getSul(key).sul_price;
            }
            return sum;
        }
    }

    private int getCustomExpense() {
        int sum = 0;
        for (ModeValuePair pair : moreInfo_list) {
            if (pair.getMode() == 2) {
                try {
                    sum += pair.getValue_asInteger();
                } catch (Exception ignored) {
                }
            }
        }
        return sum;
    }

    public ArrayList<String> getFriend_list() {
        ArrayList<String> returnArray = new ArrayList<>();
        for (ModeValuePair e : moreInfo_list) {
            if (e.getMode() == 0) {
                if (!returnArray.contains(e.getValue())) {
                    returnArray.add(e.getValue());
                }
            }
        }
        return returnArray;
    }

    public ArrayList<String> getLocation_list() {
        ArrayList<String> returnArray = new ArrayList<>();
        for (ModeValuePair e : moreInfo_list) {
            if (e.getMode() == 0) {
                if (!returnArray.contains(e.getValue())) {
                    returnArray.add(e.getValue());
                }
            }
        }
        return returnArray;
    }
}
