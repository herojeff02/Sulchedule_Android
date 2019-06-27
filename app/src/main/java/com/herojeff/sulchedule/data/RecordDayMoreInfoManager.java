package com.herojeff.sulchedule.data;

import java.util.ArrayList;

public class RecordDayMoreInfoManager {
    private ArrayList<RecordDayMoreInfoItem> recordDayMoreInfoItems;

    public RecordDayMoreInfoManager() {
        recordDayMoreInfoItems = new ArrayList<>();
    }

    public RecordDayMoreInfoItem get(int pos) {
        return recordDayMoreInfoItems.get(pos);
    }

    public int getCustomExpense() {
        return 0;
    }

    public boolean isCustomExpenseEnabled() {
        return false;
    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return recordDayMoreInfoItems.size();
    }

    public ArrayList<String> getFriendList() {
        ArrayList<String> returnArray = new ArrayList<>();
        for (RecordDayMoreInfoItem item : recordDayMoreInfoItems) {
            returnArray.addAll(item.getFriendList());
        }
        return returnArray;
    }

    public ArrayList<String> getLocationList() {
        ArrayList<String> returnArray = new ArrayList<>();
        for (RecordDayMoreInfoItem item : recordDayMoreInfoItems) {
            returnArray.add(item.getLocation());
        }
        return returnArray;
    }
}
