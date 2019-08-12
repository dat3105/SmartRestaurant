package com.example.articleapps.Objectt;

public class MainMenuObject {
    private String title;
    private int img;
    private int icon;

    public MainMenuObject(String title, int img, int icon) {
        this.title = title;
        this.img = img;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
