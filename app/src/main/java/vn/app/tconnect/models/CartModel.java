package vn.app.tconnect.models;

public class CartModel {

    int productPrice;
    String productImage;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    String documentId;

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    String totalQuantity;
    String productName;
    int tongtien;
    public CartModel() {
    }




    public CartModel(int productPrice, String productImage, String totalQuantity, String productName, int tongtien) {
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.totalQuantity = totalQuantity;
        this.productName = productName;
        this.tongtien = tongtien;
    }


}
