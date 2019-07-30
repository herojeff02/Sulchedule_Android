package com.herojeff.sulchedule.data;

import java.util.ArrayList;
import java.util.HashSet;

public class RecordDayMemoManager {
    private ArrayList<RecordDayMemoItem> recordDayMemoItems;

    public RecordDayMemoManager() {
        recordDayMemoItems = new ArrayList<>();
    }

    public RecordDayMemoItem get(int pos) {
        return recordDayMemoItems.get(pos);
    }

    public int getCustomExpense() {
        int sum = 0;
        for (RecordDayMemoItem item : recordDayMemoItems) {
            if(item.isCustomExpenseEnabled()){
                sum += item.getCustomExpense();
            }
        }
        return sum;
    }

    public boolean isCustomExpenseEnabled() {
        for (RecordDayMemoItem item : recordDayMemoItems) {
            if(item.isCustomExpenseEnabled()){
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return recordDayMemoItems.size();
    }

    public ArrayList<String> getFriendList() {
        ArrayList<String> returnArray = new ArrayList<>();
        for (RecordDayMemoItem item : recordDayMemoItems) {
            returnArray.addAll(item.getFriendArrayList());
        }
        returnArray = new ArrayList<>(new HashSet<>(returnArray));
        return returnArray;
    }

    public ArrayList<String> getLocationList() {
        ArrayList<String> returnArray = new ArrayList<>();
        for (RecordDayMemoItem item : recordDayMemoItems) {
            returnArray.addAll(item.getLocationArrayList());
        }
        returnArray = new ArrayList<>(new HashSet<>(returnArray));
        return returnArray;
    }
}