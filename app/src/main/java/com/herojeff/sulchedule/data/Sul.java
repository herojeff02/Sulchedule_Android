package com.herojeff.sulchedule.data;

public class Sul {
    String sul_name;

    public Sul(String sul_name, int sul_calorie, int sul_price, String sul_unit) {
        this.sul_name = sul_name;
        this.sul_calorie = sul_calorie;
        this.sul_price = sul_price;
        this.sul_unit = sul_unit;
//        this.sul_percentage = sul_percentage;
    }

    public int sul_calorie;
    public int sul_price;
    public String sul_unit;
//    public double sul_percentage;

    public boolean isSul_enabled() {
        return sul_enabled;
    }

    public void disableSul() {
        this.sul_enabled = false;
    }

    boolean sul_enabled = true;
}