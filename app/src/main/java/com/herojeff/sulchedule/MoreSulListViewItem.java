package com.herojeff.sulchedule;

public class MoreSulListViewItem {
    String sul_name;
    int sul_count;
    boolean favourite;

    public MoreSulListViewItem(String sul_name, int sul_count, boolean favourite) {
        this.sul_name = sul_name;
        this.sul_count = sul_count;
        this.favourite = favourite;
    }

    public String getSul_name() {
        return sul_name;
    }

    public void setSul_name(String sul_name) {
        this.sul_name = sul_name;
    }

    public int getSul_count() {
        return sul_count;
    }

    public void setSul_count(int sul_count) {
        this.sul_count = sul_count;
    }

    public int getFavourite() {
        return sul_count;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
