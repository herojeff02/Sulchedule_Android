package com.herojeff.sulchedule;

public class MoreInfoListViewItem {
        public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public MoreInfoListViewItem(String content, int mode) {
        this.content = content;
        this.mode = mode;
    }

    String content;
    int mode;
}
