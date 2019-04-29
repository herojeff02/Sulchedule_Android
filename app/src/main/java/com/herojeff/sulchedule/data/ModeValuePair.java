package com.herojeff.sulchedule.data;

public class ModeValuePair {
    public ModeValuePair(int mode, String value) {
        this.mode = mode;
        this.value = value;
    }
    public ModeValuePair(int mode, int value) {
        this.mode = mode;
        this.value = String.valueOf(value);
    }

    public int getValue_asInteger(){
        try{
            return Integer.valueOf(value);
        }catch (Exception e){
            return -1;
        }
    }

    public String getValue(){
        return value;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    private int mode;
    private String value;
}