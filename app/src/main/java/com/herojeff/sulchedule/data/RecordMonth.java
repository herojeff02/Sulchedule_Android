package com.herojeff.sulchedule.data;

public class RecordMonth {
    public RecordMonth(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public int year;
    public int month;

    boolean enable_daysOfMonth;
    boolean enable_streakOfMonth;
    boolean enable_caloriesOfMonth;
    boolean enable_totalExpense;

    public boolean isEnable_daysOfMonth() {
        return enable_daysOfMonth;
    }

    public void setEnable_daysOfMonth(boolean enable_daysOfMonth) {
        this.enable_daysOfMonth = enable_daysOfMonth;
    }

    public boolean isEnable_streakOfMonth() {
        return enable_streakOfMonth;
    }

    public void setEnable_streakOfMonth(boolean enable_streakOfMonth) {
        this.enable_streakOfMonth = enable_streakOfMonth;
    }

    public boolean isEnable_caloriesOfMonth() {
        return enable_caloriesOfMonth;
    }

    public void setEnable_caloriesOfMonth(boolean enable_caloriesOfMonth) {
        this.enable_caloriesOfMonth = enable_caloriesOfMonth;
    }

    public boolean isEnable_totalExpense() {
        return enable_totalExpense;
    }

    public void setEnable_totalExpense(boolean enable_totalExpense) {
        this.enable_totalExpense = enable_totalExpense;
    }

    public int getGoal_daysOfMonth() {
        return goal_daysOfMonth;
    }

    public void setGoal_daysOfMonth(int goal_daysOfMonth) {
        this.goal_daysOfMonth = goal_daysOfMonth;
    }

    public int getGoal_streakOfMonth() {
        return goal_streakOfMonth;
    }

    public void setGoal_streakOfMonth(int goal_streakOfMonth) {
        this.goal_streakOfMonth = goal_streakOfMonth;
    }

    public int getGoal_caloriesOfMonth() {
        return goal_caloriesOfMonth;
    }

    public void setGoal_caloriesOfMonth(int goal_caloriesOfMonth) {
        this.goal_caloriesOfMonth = goal_caloriesOfMonth;
    }

    public int getGoal_totalExpense() {
        return goal_totalExpense;
    }

    public void setGoal_totalExpense(int goal_totalExpense) {
        this.goal_totalExpense = goal_totalExpense;
    }

    public boolean isFirst_launch_of_month() {
        return first_launch_of_month;
    }

    public void disableirst_launch_of_month() {
        this.first_launch_of_month = false;
    }

    public double goalStat_daysOfMonth(){
        /**add calculation here;**/
        return 0.7;
    }
    public double goalStat_streakOfMonth(){
        /**add calculation here;**/
        return 0.7;
    }
    public double goalStat_caloriesOfMonth(){
        /**add calculation here;**/
        return 0.7;
    }
    public double goalStat_totalExpense(){
        /**add calculation here;**/
        return 0.7;
    }

    int goal_daysOfMonth;
    int goal_streakOfMonth;
    int goal_caloriesOfMonth;
    int goal_totalExpense;

    boolean first_launch_of_month = true;
}
