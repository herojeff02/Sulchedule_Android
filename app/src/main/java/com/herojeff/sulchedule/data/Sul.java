package com.herojeff.sulchedule.data;

public class Sul {
    public Sul(String sul_name, int sul_calorie, int sul_price, String sul_unit) {
        this.sul_name = sul_name;
        this.sul_calorie = sul_calorie;
        this.sul_price = sul_price;
        this.sul_unit = sul_unit;
    }

    String sul_name;
    public int sul_calorie;
    public int sul_price;

    public void setSul_name(String sul_name) {
        this.sul_name = sul_name;
    }

    public void setSul_calorie(int sul_calorie) {
        this.sul_calorie = sul_calorie;
    }

    public void setSul_price(int sul_price) {
        this.sul_price = sul_price;
    }

    public void setSul_unit(String sul_unit) {
        this.sul_unit = sul_unit;
    }

    public String sul_unit;

    public boolean isSul_enabled() {
        return sul_enabled;
    }

    public void disableSul() {
        this.sul_enabled = false;
    }

    boolean sul_enabled = true;
    boolean favourite = false;

    public void toggleFavourite(){
        favourite = !favourite;
    }
    public boolean isFavourite(){
        return sul_enabled && favourite;
    }
}