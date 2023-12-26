package vn.app.tconnect.models;

import java.io.Serializable;

import vn.app.tconnect.ProductModel;

public class ChuotModel implements Serializable, ProductModel {
    public ChuotModel() {
    }

    String name;
    String desc;

    public ChuotModel(String desc) {
        this.desc = desc;
    }

    int price;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    String img_url;

    public ChuotModel(String name, int price, String img_url) {
        this.name = name;
        this.price = price;
        this.img_url = img_url;
    }
}

