package vn.app.tconnect.models;

import java.io.Serializable;

import vn.app.tconnect.ProductModel;

public class BanPhimModel implements Serializable, ProductModel {
    public BanPhimModel() {
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    String name;
    int price;
    String img_url;
    String desc;

    public BanPhimModel(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BanPhimModel(String name, int price, String img_url) {
        this.name = name;
        this.price = price;
        this.img_url = img_url;
    }
}
