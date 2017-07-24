package com.esp.hangul.Home;

public class StudyItem {

    private String title;
    private int imageResId;
    private int color;

    public StudyItem(String title) {
        this.title = title;
    }

    public StudyItem(String title, int imageResId) {
        this.title = title;
        this.imageResId = imageResId;
    }

    public StudyItem(String title, int imageResId, int color) {
        this.title = title;
        this.imageResId = imageResId;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
