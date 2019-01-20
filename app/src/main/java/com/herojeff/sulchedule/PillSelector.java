package com.herojeff.sulchedule;

public class PillSelector {
    int mode;
    public static int whom = 1;
    public static int where = 2;
    public static int expense = 3;

    public int getMode() {
        return mode;
    }

    public String getModeString(){
        switch (mode){
            case 1:
                return "누구와";
            case 2:
                return "어디서";
            case 3:
                return "얼마";
        }
        return "";
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public PillSelector(int mode){
        this.mode = mode;
    }

}
