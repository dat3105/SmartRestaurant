package com.example.articleapps.TableMenu.Model;

public class ban {
    int id;
    String name;
    int price;
    int img;
    public ban(){}
    public ban(int id,String name,int price,int img){
        this.id=id;
        this.name=name;
        this.price=price;
        this.img=img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
