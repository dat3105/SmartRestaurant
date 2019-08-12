package com.example.articleapps.TableMenu.Model;

public class DataPremiumTable {
    private String title;
    private String price;
    private int img;

    public DataPremiumTable() {
    }

    public DataPremiumTable(String title, String price, int img) {
        this.title = title;
        this.price = price;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
