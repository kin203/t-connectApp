package vn.app.tconnect.models;

import java.io.Serializable;

import vn.app.tconnect.ProductModel;

public class RecommendedModel implements Serializable, ProductModel {


    String name;
    String desc;

    public RecommendedModel(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    int price;
    String img_url;
    public RecommendedModel() {
    }


    public RecommendedModel(String name, int price, String img_url) {
        this.name = name;
        this.price = price;
        this.img_url = img_url;
    }





}
