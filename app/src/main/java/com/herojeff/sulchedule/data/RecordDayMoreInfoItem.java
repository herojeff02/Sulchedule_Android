package com.herojeff.sulchedule.data;

import java.util.ArrayList;

public class RecordDayMoreInfoItem {

    ArrayList<String> friendList;
    String location = "";
    int customExpense = 0;
    boolean customExpenseEnabled = false;

    public ArrayList<String> getFriendList() {
        return friendList;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isEmpty() {
        return friendList.size() == 0 && location.length() == 0 & !isCustomExpenseEnabled();
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

    public void addFriend(String friend) {
        if (friend != null && friend.length() != 0) {
            friendList.add(friend);
        }
    }

    public void removeFriend(String friend) {
        friendList.remove(friend);
    }

    public void removeLocation() {
        location = "";
    }
}
