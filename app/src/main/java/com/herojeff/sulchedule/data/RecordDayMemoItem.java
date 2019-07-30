package com.herojeff.sulchedule.data;

import java.util.ArrayList;

public class RecordDayMemoItem {

    private String memo = "";

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    private int customExpense = 0;

    public void setCustomExpenseEnabled(boolean customExpenseEnabled) {
        this.customExpenseEnabled = customExpenseEnabled;
    }

    private boolean customExpenseEnabled = false;

    public ArrayList<String> getFriendArrayList() {
        return findStringsFromArrayList("@");
    }

    public ArrayList<String> getLocationArrayList() {
        return findStringsFromArrayList("#");
    }

    private String[] stringSplitter(){
        return memo.split(", ");
    }

    private ArrayList<String> findStringsFromArrayList(String key){
        ArrayList<String> returnArray = new ArrayList<>();
        for(String item : stringSplitter()){
            if(item.startsWith(key)){
                returnArray.add(item.substring(1));
            }
        }
        return returnArray;
    }

    public boolean isEmpty() {
        return getFriendArrayList().size() == 0 && getLocationArrayList().size() == 0 & !isCustomExpenseEnabled();
    }

    public boolean isCustomExpenseEnabled() {
        return customExpenseEnabled;
    }

    public int getCustomExpense() {
        return customExpense;
    }

    public void setCustomExpense(int expense) {
        customExpense = expense;
        customExpenseEnabled = true;
    }
}
